package net.dev1m.shopgame.controller;

import net.dev1m.shopgame.dto.GroupGameDTO;
import net.dev1m.shopgame.payLoad.ResponseData;
import net.dev1m.shopgame.service.imp.FileServiceImp;
import net.dev1m.shopgame.service.imp.GroupGameServiceImp;
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
@RequestMapping("/group")
public class GroupGameController {
    @Autowired
    FileServiceImp fileServiceImp;
    @Autowired
    GroupGameServiceImp groupGameServiceImp;
    @PostMapping("/addGroupGame")
    public ResponseEntity<?> createGroupGame(
            @RequestParam int category_id
            ,@RequestParam String title
            ,@RequestParam String display
            ,@RequestParam(value = "img", required = false) MultipartFile img
            ,@RequestParam String description)
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = groupGameServiceImp.insertGroupGame(category_id,title,display,img,description);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> listGroupGame(){
        ResponseData responseData = new ResponseData();
        responseData.setData(groupGameServiceImp.listGroupGame());
        return new  ResponseEntity<>(responseData, HttpStatus.OK);

    }
    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileGroup(@PathVariable String filename){
        Resource resource = fileServiceImp.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);

    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getGroupsByCategoryId(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        List<GroupGameDTO> groupGameDTOS = groupGameServiceImp.getGroupsByCategoryId(id);
        if (!groupGameDTOS.isEmpty()) {
            responseData.setData(groupGameDTOS);
        } else {
            responseData.setData(false);
            responseData.setSuccess(false);
        }
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/count/{id}")
    public ResponseEntity<?> countGroupsByCategoryId(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(groupGameServiceImp.countGroupByCategoryIdAndDisplay(id));
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/getGroupId/{id}")
    public ResponseEntity<?> getGroupsById(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        GroupGameDTO groupGameDTO = groupGameServiceImp.getGroupById(id);
        responseData.setData(groupGameDTO);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateGroup(
            @RequestParam int id,
            @RequestParam String title,
            @RequestParam String display,
            @RequestParam(value = "img", required = false) MultipartFile img,
            @RequestParam String description
    )
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = groupGameServiceImp.updateById(id,title,display,img,description);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteByid(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(groupGameServiceImp.deleteById(id));
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
