package net.dev1m.shopgame.service;

import net.dev1m.shopgame.dto.CategoryCayThueDTO;
import net.dev1m.shopgame.entity.Category_caythue;
import net.dev1m.shopgame.reponsitory.CategoryCayThueReponsitory;
import net.dev1m.shopgame.service.imp.CategoryCayThueServiceImp;
import net.dev1m.shopgame.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryCayThueService implements CategoryCayThueServiceImp {
    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    CategoryCayThueReponsitory categoryCayThueReponsitory;
    @Override
    public boolean insertCategoryCayThue(String title, String display, MultipartFile img) {
        boolean isIsertSuccess = false;
        boolean isSaveFileSuccess = fileServiceImp.savefile(img);
        if(isSaveFileSuccess){
            Category_caythue category_caythue = new Category_caythue();
            category_caythue.setTitle(title);
            category_caythue.setDisplay(display);
            category_caythue.setImg(img.getOriginalFilename());
            categoryCayThueReponsitory.save(category_caythue);
            isIsertSuccess = true;
        }
        return isIsertSuccess;
    }

    @Override
    public List<CategoryCayThueDTO> listCategoryCayThue() {
        List<Category_caythue> listCategoryCayThue = categoryCayThueReponsitory.findAll();
        List<CategoryCayThueDTO> categoryCayThueDTOS = new ArrayList<>();
        for (Category_caythue data : listCategoryCayThue) {
            CategoryCayThueDTO categoryCayThueDTO = new CategoryCayThueDTO();
            categoryCayThueDTO.setId(data.getId());
            categoryCayThueDTO.setTitle(data.getTitle());
            categoryCayThueDTO.setDisplay(data.getDisplay());
            categoryCayThueDTO.setImg(data.getImg());
            categoryCayThueDTOS.add(categoryCayThueDTO);
        }
        return categoryCayThueDTOS;
    }

    @Override
    public CategoryCayThueDTO getCategoryCayThueById(int id) {
        Category_caythue category_caythue = categoryCayThueReponsitory.findById(id);
        CategoryCayThueDTO categoryCayThueDTO = new CategoryCayThueDTO();
        categoryCayThueDTO.setId(category_caythue.getId());
        categoryCayThueDTO.setTitle(category_caythue.getTitle());
        categoryCayThueDTO.setDisplay(category_caythue.getDisplay());
        categoryCayThueDTO.setImg(category_caythue.getImg());
        return categoryCayThueDTO;
    }

    @Override
    public boolean updateCategoryCayThue(int id, String title, String display, MultipartFile img) {
        Category_caythue category_caythue = categoryCayThueReponsitory.findById(id);
        if(category_caythue != null){
            category_caythue.setTitle(title);
            category_caythue.setDisplay(display);
            if(img != null){
                boolean isSaveFile = fileServiceImp.savefile(img);
                if(isSaveFile) {
                    category_caythue.setImg(img.getOriginalFilename());
                }
            }else{
                category_caythue.setImg(category_caythue.getImg());
            }
            categoryCayThueReponsitory.save(category_caythue);
            return  true;
        }
        else {
            return false;
        }

    }

    @Override
    public boolean deleteById(int id) {
        try {
            categoryCayThueReponsitory.deleteById(id);
            return true; // Trả về true nếu xóa thành công
        } catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return false;
        }
    }


}
