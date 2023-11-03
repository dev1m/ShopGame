package net.dev1m.shopgame.service;

import net.dev1m.shopgame.dto.GroupGameDTO;
import net.dev1m.shopgame.entity.Category;
import net.dev1m.shopgame.entity.Groups;
import net.dev1m.shopgame.reponsitory.GroupGameReponsitory;
import net.dev1m.shopgame.service.imp.FileServiceImp;
import net.dev1m.shopgame.service.imp.GroupGameServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class GroupGameService implements GroupGameServiceImp {
    @Autowired
    FileServiceImp fileServiceImp;
    @Autowired
    GroupGameReponsitory groupGameReponsitory;

    @Override
    public boolean insertGroupGame(int category_id, String title, String display, MultipartFile img, String description) {
        boolean isIsertSuccess = false;
       try {
           boolean isSaveFileSuccess = fileServiceImp.savefile(img);
           if(isSaveFileSuccess){
               Groups group = new Groups();
               Category category = new Category();
               category.setId(category_id);
               group.setCategory(category);
               group.setTitle(title);
               group.setDisplay(display);
               group.setImg(img.getOriginalFilename());
               group.setDescription(description);
               groupGameReponsitory.save(group);
               isIsertSuccess = true;
           }
       }catch (Exception e){
           System.out.println("Error insert group "+e.getMessage());
       }
        return isIsertSuccess ;
    }

    @Override
    public List<GroupGameDTO> listGroupGame() {
        List<Groups> listGroup = groupGameReponsitory.findAll();
        List<GroupGameDTO> listGroupGame = new ArrayList<>();
        for (Groups groups : listGroup) {
            GroupGameDTO groupGameDTO = new GroupGameDTO();
            groupGameDTO.setId(groups.getId());
            groupGameDTO.setCategory_id(groups.getId());
            groupGameDTO.setTitle(groups.getTitle());
            groupGameDTO.setDisplay(groups.getDisplay());
            groupGameDTO.setImg(groups.getImg());
            groupGameDTO.setDescription(groups.getDescription());
            listGroupGame.add(groupGameDTO);
        }
        return listGroupGame;
    }

    @Override
    public List<GroupGameDTO> getGroupsByCategoryId(int id) {
        Optional<List<Groups>> optionalGroups = groupGameReponsitory.findByCategory_Id(id);
        if(optionalGroups.isPresent()){
            List<Groups> groups = optionalGroups.get();
            List<GroupGameDTO> groupGameDTOS = new ArrayList<>();
            for (Groups data : groups) {
                GroupGameDTO groupGameDTO = new GroupGameDTO();
                groupGameDTO.setId(data.getId());
                groupGameDTO.setCategory_id(data.getCategory().getId());
                groupGameDTO.setTitle(data.getTitle());
                groupGameDTO.setDisplay(data.getDisplay());
                groupGameDTO.setImg(data.getImg());
                groupGameDTO.setDescription(data.getDescription());
                groupGameDTOS.add(groupGameDTO);
            }
            return groupGameDTOS;
        }else {
            return Collections.emptyList(); // Trả về danh sách trống nếu không tìm thấy dữ liệu
        }
    }

    @Override
    public int countGroupByCategoryIdAndDisplay(int id) {
        return groupGameReponsitory.countByCategoryIdAndDisplay(id, "SHOW");
    }

    @Override
    public GroupGameDTO getGroupById(int id) {
        Groups groups = groupGameReponsitory.findById(id);
        if (groups != null) {
            GroupGameDTO groupGameDTO = new GroupGameDTO();
            groupGameDTO.setId(groups.getId());
            groupGameDTO.setCategory_id(groups.getCategory().getId());
            groupGameDTO.setTitle(groups.getTitle());
            groupGameDTO.setDisplay(groups.getDisplay());
            groupGameDTO.setDescription(groups.getDescription());
            groupGameDTO.setImg(groups.getImg());
            return groupGameDTO;

        } else {
            return null; // Xử lý trường hợp không tìm thấy người dùng
        }
    }

    @Override
    public boolean updateById(int id,String title,String display,MultipartFile img,String description) {
        Groups groups = groupGameReponsitory.findById(id);
        if(groups != null){
            if(img == null || img.isEmpty()){
                groups.setImg(groups.getImg());
            }else {
                boolean isSaveFile = fileServiceImp.savefile(img);
                if(isSaveFile) {
                    groups.setImg(img.getOriginalFilename());
                }
            }
            if(description == null || description.isEmpty()){
                groups.setDescription(groups.getDescription());
            }else {
                groups.setDescription(description);
            }
            groups.setId(id);
            groups.setTitle(title);
            groups.setDisplay(display);
            groupGameReponsitory.save(groups);
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public boolean deleteById(int id) {
        try {
            groupGameReponsitory.deleteById(id);
            return true; // Trả về true nếu xóa thành công
        } catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return false;
        }
    }

}
