package kcl.ac.uk.kaiji_machine.dto;

import lombok.Data;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */

@Data
public class UserDto {
    String username;
    String email;
    String password;
    String token;
}
