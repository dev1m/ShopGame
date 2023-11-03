package net.dev1m.shopgame.controller;

import net.dev1m.shopgame.dto.UserDTO;
import net.dev1m.shopgame.payLoad.ResponseData;
import net.dev1m.shopgame.service.imp.AuthServiceImp;
import net.dev1m.shopgame.util.JwtUtilHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    JwtUtilHelper jwtUtilHelper;
    @Autowired
    AuthServiceImp authServiceImp;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password ){
        ResponseData responseData = new ResponseData();
        String loginStatus = authServiceImp.checkLogin(username, password);
        if ("LoginSuccess".equals(loginStatus)) {
            String token = jwtUtilHelper.generateToken(username);
            responseData.setData(token);
        } else if ("UserNotFound".equals(loginStatus)) {
            // Tên người dùng không tồn tại
            responseData.setData("UserNotFound");
            responseData.setSuccess(false);
        } else if ("PasswordMismatch".equals(loginStatus)) {
            // Mật khẩu không khớp
            responseData.setData("PasswordMismatch");
            responseData.setSuccess(false);
        } else {
            responseData.setData(false);
            responseData.setSuccess(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
        ResponseData responseData = new ResponseData();
        String registerStatus = authServiceImp.register(userDTO);
        if("RegisterSuccess".equals(registerStatus)){
            responseData.setData("RegisterSuccess");
            responseData.setSuccess(true);
        } else if ("UserExists".equals(registerStatus)) {
            responseData.setData("UserExists");
            responseData.setSuccess(false);
        } else if ("EmailInvalid".equals(registerStatus)) {
            responseData.setData("EmailInvalid");
            responseData.setSuccess(false);
        }else {
            responseData.setData(false);
            responseData.setSuccess(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestParam String username, @RequestParam String newPassword ){
        ResponseData responseData = new ResponseData();
        responseData.setData(authServiceImp.changePassword(username,newPassword));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("email") String email) {
            ResponseData responseData = new ResponseData();
            responseData.setData(authServiceImp.resetPasswordAndSendEmail(email));
            return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
