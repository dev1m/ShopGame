package net.dev1m.shopgame.service.imp;
import net.dev1m.shopgame.dto.CategoryGameDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryGameServiceImp {
    boolean insertCategoryGame( String title,String display,MultipartFile img);
    List<CategoryGameDTO> listCategoryGame();
    String getTitleById(int id);
    boolean updateCategory(int id,String title,String display,MultipartFile img);
    boolean deleteById(int id);

    CategoryGameDTO getCategoryById(int id);

}
