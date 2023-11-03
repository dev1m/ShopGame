package net.dev1m.shopgame.controller;


import net.dev1m.shopgame.dto.CashFlowDTO;
import net.dev1m.shopgame.entity.Cash_flow;
import net.dev1m.shopgame.payLoad.ResponseData;
import net.dev1m.shopgame.service.imp.CashFlowServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/cashFlow")
public class CashFlowController {
    @Autowired
    CashFlowServiceImp cashFlowServiceImp;
    @PostMapping("/addCashFlow")
    public ResponseEntity<?> createCashFlow(
            @RequestParam  int cashOld
            ,@RequestParam int cashChange
            ,@RequestParam int cashNew
            ,@RequestParam String cashNote
            ,@RequestParam int userId)
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = cashFlowServiceImp.addCashFlow(cashOld,cashChange,cashNew,cashNote,userId);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<?> getAccountsByUserId(@PathVariable int userId) {
        ResponseData responseData = new ResponseData();
        List<CashFlowDTO> cashFlowDTOS = cashFlowServiceImp.getCash_FLowByUserId(userId);
        if (!cashFlowDTOS.isEmpty()) {
            responseData.setData(cashFlowDTOS);
        } else {
            responseData.setData(false);
            responseData.setSuccess(false);
        }
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCashFlow() {
        ResponseData responseData = new ResponseData();
        responseData.setData(cashFlowServiceImp.getAllCashFlows());
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
