package net.dev1m.shopgame.service;

import net.dev1m.shopgame.dto.CategoryGameDTO;
import net.dev1m.shopgame.entity.Category;
import net.dev1m.shopgame.reponsitory.CategoryGameReponsitory;
import net.dev1m.shopgame.service.imp.CategoryGameServiceImp;
import net.dev1m.shopgame.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryGameService implements CategoryGameServiceImp {
    @Autowired
    CategoryGameReponsitory categoryGameReponsitory;
    @Autowired
    FileServiceImp fileServiceImp;
    @Override
    public boolean insertCategoryGame(String title, String display, MultipartFile img) {
        boolean isIsertSuccess = false;
        boolean isSaveFileSuccess = fileServiceImp.savefile(img);
        if(isSaveFileSuccess){
            Category category = new Category();
            category.setTitle(title);
            category.setDisplay(display);
            category.setImg(img.getOriginalFilename());
            categoryGameReponsitory.save(category);
            isIsertSuccess = true;
        }
        return isIsertSuccess;
    }

    @Override
    public List<CategoryGameDTO> listCategoryGame() {
        List<Category> listCategory = categoryGameReponsitory.findAll();
        List<CategoryGameDTO> listCategoryGame = new ArrayList<>();
        for (Category category : listCategory) {
            CategoryGameDTO categoryGameDTO = new CategoryGameDTO();
            categoryGameDTO.setId(category.getId());
            categoryGameDTO.setTitle(category.getTitle());
            categoryGameDTO.setDisplay(category.getDisplay());
            categoryGameDTO.setImg(category.getImg());
            listCategoryGame.add(categoryGameDTO);
        }
        return listCategoryGame;
    }

    @Override
    public String getTitleById(int id) {
        Category category = categoryGameReponsitory.findById(id).orElse(null);
        if (category != null) {
            return category.getTitle();
        }
        return null;
    }

    @Override
    public boolean updateCategory(int id, String title, String display, MultipartFile img) {
        Optional<Category> category = categoryGameReponsitory.findById(id);
        if(category.isPresent()){
            Category category1 = category.get();
            category1.setTitle(title);
            category1.setDisplay(display);
            if(img != null){
                boolean isSaveFile = fileServiceImp.savefile(img);
                if(isSaveFile) {
                    category1.setImg(img.getOriginalFilename());
                }
            }else{
                category1.setImg(category1.getImg());
            }
            categoryGameReponsitory.save(category1);
            return  true;
        }
        else {
            return false;
        }

    }

    @Override
    public boolean deleteById(int id) {
        try {
            categoryGameReponsitory.deleteById(id);
            return true; // Trả về true nếu xóa thành công
        } catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return false;
        }
    }

    @Override
    public CategoryGameDTO getCategoryById(int id) {
        Optional<Category> category = categoryGameReponsitory.findById(id);
        if(category.isPresent()){
            Category category1 = category.get();
            CategoryGameDTO categoryGameDTO = new CategoryGameDTO();
            categoryGameDTO.setId(category1.getId());
            categoryGameDTO.setTitle(category1.getTitle());
            categoryGameDTO.setImg(category1.getImg());
            categoryGameDTO.setDisplay(category1.getDisplay());
            return  categoryGameDTO;
        }else {
            return null;
        }

    }

}
