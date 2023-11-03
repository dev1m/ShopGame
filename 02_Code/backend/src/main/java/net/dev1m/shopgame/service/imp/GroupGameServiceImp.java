package net.dev1m.shopgame.service.imp;
import net.dev1m.shopgame.dto.GroupGameDTO;
import net.dev1m.shopgame.entity.Groups;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GroupGameServiceImp {
    boolean insertGroupGame(int category_id,String title,String display,MultipartFile img,String description);
    List<GroupGameDTO> listGroupGame();
    List<GroupGameDTO> getGroupsByCategoryId(int id);
    int countGroupByCategoryIdAndDisplay(int id);
    GroupGameDTO getGroupById(int id);
    boolean updateById(int id,String title,String display,MultipartFile img,String description);
    boolean deleteById(int id);

}
