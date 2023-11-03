package net.dev1m.shopgame.controller;

import net.dev1m.shopgame.dto.GroupCayThueDTO;
import net.dev1m.shopgame.payLoad.ResponseData;
import net.dev1m.shopgame.service.imp.GroupCayThueServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/group_caythue")
public class GroupCayThueController {
    @Autowired
    GroupCayThueServiceImp groupCayThueServiceImp;
    @PostMapping("/addGroupCayThue")
    public ResponseEntity<?> createGroupCayThue(
            @RequestParam int category_caythue_id
            ,@RequestParam String title
            ,@RequestParam String display
            ,@RequestParam int money)
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = groupCayThueServiceImp.insertGroupCayThue(category_caythue_id,title,display,money);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getGroupCaythuesByCategoryId(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        List<GroupCayThueDTO> groupCaythues = groupCayThueServiceImp.getGroupCaythuesByCategoryId(id);
        if (!groupCaythues.isEmpty()) {
            responseData.setData(groupCaythues);
        } else {
            responseData.setData(false);
            responseData.setSuccess(false);
        }
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/count/{id}")
    public ResponseEntity<?> countGroupCaythuesByCategoryId(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        int count = groupCayThueServiceImp.countGroupCaythuesByCategoryIdAndDisplay(id);
        responseData.setData(count);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/getGroupCayThue/{id}")
    public ResponseEntity<?> getGroupCayThueById(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        GroupCayThueDTO groupCayThueDTO = groupCayThueServiceImp.getById(id);
        responseData.setData(groupCayThueDTO);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteByid(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(groupCayThueServiceImp.deleteById(id));
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateGroupCayThue(
            @RequestParam int id,
            @RequestParam String title,
            @RequestParam String display,
            @RequestParam int money
    )
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = groupCayThueServiceImp.updateById(id,title,display,money);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
