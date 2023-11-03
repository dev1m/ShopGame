package net.dev1m.shopgame.service;

import net.dev1m.shopgame.dto.OrderCayThueDTO;
import net.dev1m.shopgame.entity.Group_caythue;
import net.dev1m.shopgame.entity.Order_caythue;
import net.dev1m.shopgame.entity.Users;
import net.dev1m.shopgame.reponsitory.OrderCayThueReponsitory;
import net.dev1m.shopgame.reponsitory.UserReponsitory;
import net.dev1m.shopgame.service.imp.OrderCayThueServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderCayThueService implements OrderCayThueServiceImp {
    @Autowired
    OrderCayThueReponsitory orderCayThueReponsitory;
    @Autowired
    UserReponsitory userReponsitory;
    @Override
    public boolean orderCayThue(int group_caythue_id, int money, String username, String password, String status, String note_user, int user_id) {
        Order_caythue orderCaythue = new Order_caythue();
        Group_caythue group_caythue = new Group_caythue();
        group_caythue.setId(group_caythue_id);
        orderCaythue.setGroup_caythue_id(group_caythue);
        orderCaythue.setMoney(money);
        orderCaythue.setUsername(username);
        orderCaythue.setPassword(password);
        orderCaythue.setStatus("xuly");
        orderCaythue.setNote_user(note_user);
        Users users = new Users();
        users.setId(user_id);
        orderCaythue.setUsers(users);
        try {
            orderCayThueReponsitory.save(orderCaythue);
            return true;
        }catch (Exception e){
            System.out.println("Error "+e.getMessage());
            return false;
        }
    }

    @Override
    public List<OrderCayThueDTO> getOrderCayThueByUserId(int userId) {
        List<Order_caythue> orderCaythues = orderCayThueReponsitory.findOrderCayThueByUserId(userId);
        List<OrderCayThueDTO> orderCayThueDTOS = new ArrayList<>();
        for (Order_caythue data :orderCaythues) {
            OrderCayThueDTO orderCayThueDTO = new OrderCayThueDTO();
            orderCayThueDTO.setGroup_caythue_id(data.getGroup_caythue_id().getId());
            orderCayThueDTO.setMoney(data.getMoney());
            orderCayThueDTO.setUsername(data.getUsername());
            orderCayThueDTO.setPassword(data.getPassword());
            orderCayThueDTO.setStatus(data.getStatus());
            orderCayThueDTO.setNote_admin(data.getNote_admin());
            orderCayThueDTO.setUser_id(data.getUsers().getId());
            orderCayThueDTOS.add(orderCayThueDTO);
        }
        return orderCayThueDTOS;
    }

    @Override
    public int countOrdersWithStatus() {
        return orderCayThueReponsitory.countOrdersWithStatusXuly();
    }

    @Override
    public List<OrderCayThueDTO> getAllOrderCayThue() {
        List<Order_caythue> orderCaythues = orderCayThueReponsitory.findAll();
        List<OrderCayThueDTO> orderCayThueDTOS = new ArrayList<>();
        for (Order_caythue data :orderCaythues) {
            OrderCayThueDTO orderCayThueDTO = new OrderCayThueDTO();
            orderCayThueDTO.setId(data.getId());
            orderCayThueDTO.setGroup_caythue_id(data.getGroup_caythue_id().getId());
            orderCayThueDTO.setMoney(data.getMoney());
            orderCayThueDTO.setUsername(data.getUsername());
            orderCayThueDTO.setPassword(data.getPassword());
            orderCayThueDTO.setStatus(data.getStatus());
            orderCayThueDTO.setNote_admin(data.getNote_admin());
            orderCayThueDTO.setNote_user(data.getNote_user());
            orderCayThueDTO.setUser_id(data.getUsers().getId());
            orderCayThueDTOS.add(orderCayThueDTO);
        }
        return orderCayThueDTOS;
    }

    @Override
    public boolean updateOrderCayThue(int id, String status, String note_admin) {
        Optional<Order_caythue> orderCaythue = orderCayThueReponsitory.findById(id);
        if(orderCaythue.isPresent()){
            Order_caythue orderCaythue1 = orderCaythue.get();
            orderCaythue1.setStatus(status);
            orderCaythue1.setNote_admin(note_admin);
            orderCayThueReponsitory.save(orderCaythue1);
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public OrderCayThueDTO getOrderCayThueById(int id) {
        Optional<Order_caythue> orderCaythue = orderCayThueReponsitory.findById(id);
        if(orderCaythue.isPresent()){
            Order_caythue orderCaythue1 = orderCaythue.get();
            OrderCayThueDTO orderCayThueDTO = new OrderCayThueDTO();
            orderCayThueDTO.setId(orderCaythue1.getId());
            orderCayThueDTO.setGroup_caythue_id(orderCaythue1.getGroup_caythue_id().getId());
            orderCayThueDTO.setStatus(orderCaythue1.getStatus());
            orderCayThueDTO.setMoney(orderCaythue1.getMoney());
            orderCayThueDTO.setUsername(orderCaythue1.getUsername());
            orderCayThueDTO.setPassword(orderCaythue1.getPassword());
            orderCayThueDTO.setNote_user(orderCaythue1.getNote_user());
            orderCayThueDTO.setNote_admin(orderCaythue1.getNote_admin());
            orderCayThueDTO.setUser_id(orderCaythue1.getUsers().getId());
            return orderCayThueDTO;
        }
        else {
            return null;
        }
    }


}
