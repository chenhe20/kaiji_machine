package kcl.ac.uk.kaiji_machine.service.impl;

import kcl.ac.uk.kaiji_machine.dao.User;
import kcl.ac.uk.kaiji_machine.dao.UserExample;
import kcl.ac.uk.kaiji_machine.enums.ErrorCodeEnum;
import kcl.ac.uk.kaiji_machine.exception.BaseException;
import kcl.ac.uk.kaiji_machine.mapper.UserMapper;
import kcl.ac.uk.kaiji_machine.service.UserService;
import kcl.ac.uk.kaiji_machine.util.JWTUtils;
import kcl.ac.uk.kaiji_machine.util.MD5Utils;
import kcl.ac.uk.kaiji_machine.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public String login(UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();

        // Validates request
        if (username == null || password == null)
            throw new BaseException(ErrorCodeEnum.INVALID_REQUEST);

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);

        // If there is no such username in the database
        if (users.size() == 0)
            throw new BaseException(ErrorCodeEnum.FAIL).setDesc("No match username");

        // Compares passwords
        User dbUser = users.get(0);
        if (MD5Utils.ReMd5PwdToDB(password, dbUser.getSalt()).equals(dbUser.getPassword()))
            return JWTUtils.getJwtToken(username);
        else
            throw new BaseException(ErrorCodeEnum.FAIL).setDesc("Password is incorrect");

    }

    @Override
    public String register(UserDto userDto) {

        String username = userDto.getUsername();
        String oriPassword = userDto.getPassword();

        // Validates request
        if (username == null || oriPassword == null) throw new BaseException(ErrorCodeEnum.INVALID_REQUEST);

        // Checks if the username has been used
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() != 0) throw new BaseException(ErrorCodeEnum.FAIL).setDesc("The username " + username + " has been used");

        // Encrypts the password
        String uuid = UUID.randomUUID().toString();
        String password = MD5Utils.ReMd5PwdToDB(userDto.getPassword(),uuid);

        // Creates the user object
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setSalt(uuid);
        user.setPassword(password);
        user.setEmail(userDto.getEmail());
        userMapper.insertSelective(user);

        // Token generation
        return JWTUtils.getJwtToken(username);
    }

    @Override
    public String loginByToken(UserDto userDto) {
        String token = userDto.getToken();
        String username = userDto.getUsername();

        if (!JWTUtils.checkToken(token))
            throw new BaseException(ErrorCodeEnum.AUTHENTICATION_FAIL).setDesc("token expires");

        if (!JWTUtils.getUsernameByJwtToken(token).equals(username))
            throw new BaseException(ErrorCodeEnum.AUTHENTICATION_FAIL);

        return JWTUtils.getJwtToken(username);
    }
}
