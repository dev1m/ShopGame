package net.dev1m.shopgame.controller;

import net.dev1m.shopgame.entity.Banks;
import net.dev1m.shopgame.payLoad.ResponseData;
import net.dev1m.shopgame.service.imp.BankServiceImp;
import net.dev1m.shopgame.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    FileServiceImp fileServiceImp;
    @Autowired
    BankServiceImp bankServiceImp;
    @PostMapping("/addBank")
    public ResponseEntity<?> createBank(
            @RequestParam String bank_number
            ,@RequestParam String bank_name
            ,@RequestParam String bank_fullname
            ,@RequestParam MultipartFile bank_logo
            ,@RequestParam String bank_note)

    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = bankServiceImp.insertBank(bank_number,bank_name,bank_fullname,bank_logo,bank_note);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);

    }
    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileBank(@PathVariable String filename){
        Resource resource = fileServiceImp.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);

    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getALlBank(){
        ResponseData responseData = new ResponseData();
        List<Banks> banks = bankServiceImp.findAll();
        responseData.setData(banks);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateBank(
            @RequestParam int id,
            @RequestParam String bank_number,
            @RequestParam String bank_name,
            @RequestParam String bank_fullname,
            @RequestParam(value = "bank_logo", required = false) MultipartFile bank_logo,
            @RequestParam String bank_note
    )
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = bankServiceImp.updateBank(id,bank_number,bank_name,bank_fullname,bank_logo,bank_note);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteByid(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(bankServiceImp.deleteById(id));
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
