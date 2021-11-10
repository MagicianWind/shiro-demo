package com.wind.shiro.controller;

import com.wind.shiro.dto.UserLoginDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author i-rain
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public String login() {
        UserLoginDTO userLoginDTO = new UserLoginDTO("admin", "123");
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        try {
            SecurityUtils.getSubject().login(usernamePasswordToken);
        } catch (IncorrectCredentialsException ice) {
            return "密码错误";
        } catch (UnknownAccountException uae) {
            return "账号不存在";
        } catch (LockedAccountException e) {
            return "账号被锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "操作频繁，请稍后再试";
        }
        return "login success";
    }

    @GetMapping("/register")
    public String register() {
        String password = "123";
        String salt = "qwer";
        SimpleHash simpleHash = new SimpleHash(Sha256Hash.ALGORITHM_NAME, password, ByteSource.Util.bytes(salt), 1000);
        System.out.println(simpleHash.toString());
        return "register success";
    }
}
