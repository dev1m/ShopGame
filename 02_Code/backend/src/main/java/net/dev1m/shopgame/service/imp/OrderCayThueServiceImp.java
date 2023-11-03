package net.dev1m.shopgame.service.imp;

import net.dev1m.shopgame.dto.OrderCayThueDTO;
import net.dev1m.shopgame.entity.Order_caythue;

import java.util.List;

public interface OrderCayThueServiceImp {
    boolean orderCayThue(int group_caythue_id,int money,String username,String password,String status,String note_user,int user_id);
    List<OrderCayThueDTO> getOrderCayThueByUserId(int userId);
    int countOrdersWithStatus();
    List<OrderCayThueDTO> getAllOrderCayThue();

    boolean updateOrderCayThue(int id,String status,String note_admin);
    OrderCayThueDTO getOrderCayThueById(int id);
}
