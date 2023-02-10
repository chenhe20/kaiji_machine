package kcl.ac.uk.kaiji_machine.service;

import kcl.ac.uk.kaiji_machine.dto.UserDto;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */
public interface UserService {

    String login(UserDto userDto);

    String register(UserDto userDto);

    String loginByToken(UserDto userDto);

}
