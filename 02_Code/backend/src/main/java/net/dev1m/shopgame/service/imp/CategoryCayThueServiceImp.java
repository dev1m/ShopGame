package net.dev1m.shopgame.service.imp;

import net.dev1m.shopgame.dto.CategoryCayThueDTO;
import net.dev1m.shopgame.entity.Category_caythue;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface CategoryCayThueServiceImp {
    boolean insertCategoryCayThue(String title, String display, MultipartFile img);
    List<CategoryCayThueDTO> listCategoryCayThue();
    CategoryCayThueDTO getCategoryCayThueById(int id);
    boolean updateCategoryCayThue(int id,String title,String display,MultipartFile img);
    boolean deleteById(int id);
}
