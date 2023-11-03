package net.dev1m.shopgame.controller;

import net.dev1m.shopgame.dto.AccountDTO;
import net.dev1m.shopgame.dto.GroupGameDTO;
import net.dev1m.shopgame.payLoad.ResponseData;
import net.dev1m.shopgame.service.imp.AccountServiceImp;
import net.dev1m.shopgame.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    FileServiceImp fileServiceImp;
    @Autowired
    AccountServiceImp accountServiceImp;

    @PostMapping("/addAccount")
    public ResponseEntity<?> createAccount(
            @RequestParam int group_id,
            @RequestParam String username_acc,
            @RequestParam String password_acc,
            @RequestParam String detail,
            @RequestParam MultipartFile img,
            @RequestParam int money,
            @RequestParam MultipartFile[] list_img)
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = accountServiceImp.addAccount(group_id,username_acc,password_acc,detail, img, money,list_img);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAccountByIdGroup(@PathVariable int id){
        ResponseData responseData = new ResponseData();
        List<AccountDTO> accountDTOS = accountServiceImp.getAccountsByGroupId(id);
        if (!accountDTOS.isEmpty()) {
            responseData.setData(accountDTOS);
        } else {
            responseData.setData(false);
            responseData.setSuccess(false);
        }
        return new  ResponseEntity<>(responseData, HttpStatus.OK);

    }
    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileAccount(@PathVariable String filename){
        Resource resource = fileServiceImp.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);

    }
    @GetMapping("/count/{id}")
    public ResponseEntity<?> countAccountByGroupId(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(accountServiceImp.countAccountsByGroupId(id));
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/getAccountId/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        AccountDTO accountDTO = accountServiceImp.getAccountById(id);
        responseData.setData(accountDTO);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/order")
    public ResponseEntity<?> orderAccount(@RequestParam int id,@RequestParam int user_id)
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = accountServiceImp.orderAccount(id,user_id);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<?> getAccountsByUserId(@PathVariable int userId) {
        ResponseData responseData = new ResponseData();
        List<AccountDTO> accountDTOS = accountServiceImp.getAccountsByUserId(userId);
        if (!accountDTOS.isEmpty()) {
            responseData.setData(accountDTOS);
        } else {
            responseData.setData(false);
            responseData.setSuccess(false);
        }
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/countAccountsByUserIdIsNull")
    public ResponseEntity<?> countAccountsByUserIdIsNull() {
        ResponseData responseData = new ResponseData();
        responseData.setData(accountServiceImp.countAccountsByUserIdIsNull());
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/countAccountsByUserIdIsNotNull")
    public ResponseEntity<?> countAccountsByUserIdIsNotNull() {
        ResponseData responseData = new ResponseData();
        responseData.setData(accountServiceImp.countAccountsByUserIdIsNotNull());
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/sumMoneySell")
    public ResponseEntity<?> sumMoneySell() {
        ResponseData responseData = new ResponseData();
        responseData.setData(accountServiceImp.sumMoneySell());
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/getAccountSold")
    public ResponseEntity<?> getAccountSold(){
        ResponseData responseData = new ResponseData();
        List<AccountDTO> accountDTOS = accountServiceImp.getAccountSold();
        if (!accountDTOS.isEmpty()) {
            responseData.setData(accountDTOS);
        } else {
            responseData.setData(false);
            responseData.setSuccess(false);
        }
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteByid(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(accountServiceImp.deleteById(id));
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateAccount(
            @RequestParam int id,
            @RequestParam String username_acc,
            @RequestParam String password_acc,
            @RequestParam String detail,
            @RequestParam(value = "img", required = false) MultipartFile img,
            @RequestParam int money,
            @RequestParam(value = "list_img", required = false) MultipartFile[] list_img)
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = accountServiceImp.updateAccount(id,username_acc,password_acc,detail, img, money,list_img);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/pt/{id}")
    public ResponseEntity<Page<AccountDTO>> getAccountsByGroupIdPaged(
            @PathVariable("id") int groupId,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AccountDTO> accountsPage = accountServiceImp.getAccountsByGroupIdPaged(groupId, pageable);
        return ResponseEntity.ok(accountsPage);
    }
}
