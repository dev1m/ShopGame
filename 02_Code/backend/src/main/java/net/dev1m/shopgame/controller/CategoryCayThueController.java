package net.dev1m.shopgame.controller;

import net.dev1m.shopgame.dto.CategoryCayThueDTO;
import net.dev1m.shopgame.payLoad.ResponseData;
import net.dev1m.shopgame.service.imp.CategoryCayThueServiceImp;
import net.dev1m.shopgame.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/category_caythue")
public class CategoryCayThueController {
    @Autowired
    FileServiceImp fileServiceImp;
    @Autowired
    CategoryCayThueServiceImp categoryCayThueServiceImp;
    @PostMapping("/addCategory_caythue")
    public ResponseEntity<?> createCategoryCayThue(
            @RequestParam String title
            ,@RequestParam String display
            ,@RequestParam MultipartFile img)
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = categoryCayThueServiceImp.insertCategoryCayThue(title,display,img);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);

    }
    @GetMapping("")
    public ResponseEntity<?> listCategoryCayThue(){
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryCayThueServiceImp.listCategoryCayThue());
        return new  ResponseEntity<>(responseData, HttpStatus.OK);

    }
    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileCategoryCayThue(@PathVariable String filename){

        Resource resource = fileServiceImp.loadFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);

    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCategoryCayThueById(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        CategoryCayThueDTO categoryCayThueDTO = categoryCayThueServiceImp.getCategoryCayThueById(id);
        if(categoryCayThueDTO != null){
            responseData.setData(categoryCayThueDTO);
        }else {
            responseData.setData(true);
            responseData.setSuccess(false);
        }

        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateCategoryCayThue(
            @RequestParam int id,
            @RequestParam String title,
            @RequestParam String display,
            @RequestParam(value = "img", required = false) MultipartFile img
    )
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = categoryCayThueServiceImp.updateCategoryCayThue(id,title,display,img);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteByid(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryCayThueServiceImp.deleteById(id));
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
