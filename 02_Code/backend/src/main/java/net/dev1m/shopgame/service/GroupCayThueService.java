package net.dev1m.shopgame.service;

import net.dev1m.shopgame.dto.GroupCayThueDTO;
import net.dev1m.shopgame.entity.Category_caythue;
import net.dev1m.shopgame.entity.Group_caythue;
import net.dev1m.shopgame.reponsitory.GroupCayThueReponsitory;
import net.dev1m.shopgame.service.imp.GroupCayThueServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupCayThueService implements GroupCayThueServiceImp {
    @Autowired
    GroupCayThueReponsitory groupCayThueReponsitory;
    @Override
    public boolean insertGroupCayThue(int category_caythue_id, String title, String display, int money) {
        boolean isSuccess = false;
        try{
            Group_caythue group_caythue = new Group_caythue();
            Category_caythue category_caythue = new Category_caythue();
            category_caythue.setId(category_caythue_id);
            group_caythue.setCategoryCaythue(category_caythue);
            group_caythue.setTitle(title);
            group_caythue.setDisplay(display);
            group_caythue.setMoney(money);
            groupCayThueReponsitory.save(group_caythue);
            isSuccess = true;
        }catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }

        return isSuccess;
    }

    @Override
    public List<GroupCayThueDTO> getGroupCaythuesByCategoryId(int id) {
        List<Group_caythue> groupCaythues = groupCayThueReponsitory.findByCategoryCaythueId(id);
        List<GroupCayThueDTO> groupCayThueDTOs = new ArrayList<>();
        for (Group_caythue groupCaythue : groupCaythues) {
            GroupCayThueDTO groupCayThueDTO = new GroupCayThueDTO();
            groupCayThueDTO.setId(groupCaythue.getId());
            groupCayThueDTO.setTitle(groupCaythue.getTitle());
            groupCayThueDTO.setDisplay(groupCaythue.getDisplay());
            groupCayThueDTO.setMoney(groupCaythue.getMoney());
            groupCayThueDTOs.add(groupCayThueDTO);
        }
        return groupCayThueDTOs;
    }

    @Override
    public int countGroupCaythuesByCategoryIdAndDisplay(int id) {
        return groupCayThueReponsitory.countByCategoryCaythueIdAndDisplay(id, "SHOW");
    }

    @Override
    public GroupCayThueDTO getById(int id) {
        Group_caythue group_caythue = groupCayThueReponsitory.findById(id);
        if (group_caythue != null) {
            GroupCayThueDTO groupCayThueDTO = new GroupCayThueDTO();
            groupCayThueDTO.setCategory_caythue_id(group_caythue.getCategoryCaythue().getId());
            groupCayThueDTO.setMoney(group_caythue.getMoney());
            groupCayThueDTO.setDisplay(group_caythue.getDisplay());
            groupCayThueDTO.setTitle(group_caythue.getTitle());
            return groupCayThueDTO;

        } else {
            return null; // Xử lý trường hợp không tìm thấy người dùng
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            groupCayThueReponsitory.deleteById(id);
            return true; // Trả về true nếu xóa thành công
        } catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateById(int id, String title, String display, int money) {
        Group_caythue group_caythue = groupCayThueReponsitory.findById(id);
        if(group_caythue != null){
            group_caythue.setId(id);
            group_caythue.setTitle(title);
            group_caythue.setDisplay(display);
            group_caythue.setMoney(money);
            groupCayThueReponsitory.save(group_caythue);
            return  true;
        }else {
            return false;
        }

    }


}
