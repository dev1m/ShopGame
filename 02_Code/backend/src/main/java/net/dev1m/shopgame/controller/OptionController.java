package net.dev1m.shopgame.controller;

import net.dev1m.shopgame.payLoad.ResponseData;
import net.dev1m.shopgame.service.imp.OptionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/option")
public class OptionController {
    @Autowired
    OptionServiceImp optionServiceImp;
    @PostMapping("/update")
    public ResponseEntity<?> updateOptions(@RequestParam Map<String, String> params) {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = optionServiceImp.updateOptions(params);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/getValue")
    public ResponseEntity<?> getValue(@RequestParam String option_key) {
        ResponseData responseData = new ResponseData();
        String option_value = optionServiceImp.getOptionValue(option_key);
        responseData.setData(option_value);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
