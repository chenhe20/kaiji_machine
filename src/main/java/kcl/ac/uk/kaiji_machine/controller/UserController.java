package kcl.ac.uk.kaiji_machine.controller;

import kcl.ac.uk.kaiji_machine.response.HandleResult;
import kcl.ac.uk.kaiji_machine.service.impl.UserServiceImpl;
import kcl.ac.uk.kaiji_machine.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public HandleResult registerUser(@RequestBody UserDto userDto) {

        String token = userService.register(userDto);

        return new HandleResult().setCode("100").setDesc("success").setData(token);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public HandleResult loginUser(@RequestBody UserDto userDto) {

        String token = userService.login(userDto);

        return new HandleResult().setCode("100").setDesc("success").setData(token);
    }

    @RequestMapping(value = "/loginByToken", method = RequestMethod.POST)
    @ResponseBody
    public HandleResult loginUserByToken(@RequestBody UserDto userDto) {

        String token = userService.loginByToken(userDto);

        return new HandleResult().setCode("100").setDesc("success").setData(token);
    }


}
