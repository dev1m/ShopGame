package net.dev1m.shopgame.controller;

import net.dev1m.shopgame.payLoad.ResponseData;
import net.dev1m.shopgame.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/get/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username){
        ResponseData responseData = new ResponseData();
        responseData.setData(userServiceImp.getUserByUsername(username));
        return new  ResponseEntity<>(responseData, HttpStatus.OK);

    }
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        ResponseData responseData = new ResponseData();
        responseData.setData(userServiceImp.getById(id));
        return new  ResponseEntity<>(responseData, HttpStatus.OK);

    }
    @GetMapping("/topNap")
    public ResponseEntity<?> getTop6Users() {
        ResponseData responseData = new ResponseData();
        responseData.setData(userServiceImp.getTop6UsersByMoney());
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/updateCashFlow")
    public ResponseEntity<?> updateCashFlowByUsername(@RequestParam String username,@RequestParam int newMoney){
        ResponseData responseData = new ResponseData();
        responseData.setData(userServiceImp.updateCashFlow(username,newMoney));
        return new  ResponseEntity<>(responseData, HttpStatus.OK);

    }
    @GetMapping("/countUsers")
    public ResponseEntity<?>  countUsers() {
        ResponseData responseData = new ResponseData();
        responseData.setData(userServiceImp.countUsers());
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/sumUserMoney")
    public ResponseEntity<?>  sumUserMoney() {
        ResponseData responseData = new ResponseData();
        responseData.setData(userServiceImp.sumUserMoney());
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser(){
        ResponseData responseData = new ResponseData();
        responseData.setData(userServiceImp.getAllUser());
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(
            @RequestParam int id,
            @RequestParam String level,
            @RequestParam int money,
            @RequestParam int banned,
                @RequestParam String password
    )
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = userServiceImp.updateUserByid(id,level,money,banned,password);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
