package com.wind.shiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author i-rain
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    private String username;

    private String password;
}
