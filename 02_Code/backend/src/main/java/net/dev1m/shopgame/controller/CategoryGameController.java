package net.dev1m.shopgame.controller;

import net.dev1m.shopgame.dto.CategoryGameDTO;
import net.dev1m.shopgame.dto.GroupGameDTO;
import net.dev1m.shopgame.payLoad.ResponseData;
import net.dev1m.shopgame.service.imp.CategoryGameServiceImp;
import net.dev1m.shopgame.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/category")
public class CategoryGameController {
    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    CategoryGameServiceImp categoryGameServiceImp;
    @PostMapping("/addCategoryGame")
    public ResponseEntity<?> createCategoryGame(
            @RequestParam String title
            ,@RequestParam String display
            ,@RequestParam MultipartFile img)
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = categoryGameServiceImp.insertCategoryGame(title,display,img);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);

    }
    @GetMapping("")
    public ResponseEntity<?> listCategoryGame(){
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryGameServiceImp.listCategoryGame());
        return new  ResponseEntity<>(responseData, HttpStatus.OK);

    }
    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileCategory(@PathVariable String filename){

        Resource resource = fileServiceImp.loadFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);

    }
    @GetMapping("/get/title/{id}")
    public ResponseEntity<?> getTitleByid(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryGameServiceImp.getTitleById(id));
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateCategory(
            @RequestParam int id,
            @RequestParam String title,
            @RequestParam String display,
            @RequestParam(value = "img", required = false) MultipartFile img
    )
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = categoryGameServiceImp.updateCategory(id,title,display,img);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteByid(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryGameServiceImp.deleteById(id));
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        CategoryGameDTO categoryGameDTO = categoryGameServiceImp.getCategoryById(id);
        responseData.setData(categoryGameDTO);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
