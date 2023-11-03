package net.dev1m.shopgame.controller;

import net.dev1m.shopgame.dto.OrderCayThueDTO;
import net.dev1m.shopgame.payLoad.ResponseData;
import net.dev1m.shopgame.service.imp.OrderCayThueServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderCayThueController {
    @Autowired
    OrderCayThueServiceImp orderCayThueServiceImp;
    private ResponseData responseData;

    @PostMapping("/orderCayThue")
    public ResponseEntity<?> orderCayThue(
            @RequestParam int group_caythue_id,
            @RequestParam int money,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String status,
            @RequestParam String note_user,
            @RequestParam int user_id
            )
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = orderCayThueServiceImp.orderCayThue(group_caythue_id,money,username,password,status,note_user,user_id);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<?> getOrderCayThueByUserId(@PathVariable int userId) {
        ResponseData responseData = new ResponseData();
        List<OrderCayThueDTO> orderCayThueDTOS = orderCayThueServiceImp.getOrderCayThueByUserId(userId);
        if (!orderCayThueDTOS.isEmpty()) {
            responseData.setData(orderCayThueDTOS);
        } else {
            responseData.setData(false);
            responseData.setSuccess(false);
        }
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/countOrderCayThue")
    public ResponseEntity<?> countOrderCayThue() {
        ResponseData responseData = new ResponseData();
        responseData.setData(orderCayThueServiceImp.countOrdersWithStatus());
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllOrderCayThue() {
        ResponseData responseData = new ResponseData();
        List<OrderCayThueDTO> orderCayThueDTOS = orderCayThueServiceImp.getAllOrderCayThue();
        if (!orderCayThueDTOS.isEmpty()) {
            responseData.setData(orderCayThueDTOS);
        } else {
            responseData.setData(false);
            responseData.setSuccess(false);
        }
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateOrderCayThue(
            @RequestParam int id,
            @RequestParam String status,
            @RequestParam String note_admin
    )
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = orderCayThueServiceImp.updateOrderCayThue(id,status,note_admin);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getOrderCayThueById(@PathVariable int id)
    {
        ResponseData responseData = new ResponseData();
        OrderCayThueDTO orderCayThueDTO = orderCayThueServiceImp.getOrderCayThueById(id);
        responseData.setData(orderCayThueDTO);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
