package net.dev1m.shopgame.service.imp;


import net.dev1m.shopgame.dto.GroupCayThueDTO;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface GroupCayThueServiceImp {
    boolean insertGroupCayThue(int category_caythue_id , String title, String display, int money);
    List<GroupCayThueDTO> getGroupCaythuesByCategoryId(int id);
    int countGroupCaythuesByCategoryIdAndDisplay(int id);
    GroupCayThueDTO getById(int id);
    boolean deleteById(int id);
    boolean updateById(int id, String title, String display, int money);

}
