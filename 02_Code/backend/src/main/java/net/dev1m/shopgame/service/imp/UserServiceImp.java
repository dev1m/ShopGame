package net.dev1m.shopgame.service.imp;

import net.dev1m.shopgame.dto.UserDTO;

import java.util.List;


public interface UserServiceImp {
    UserDTO getUserByUsername(String username);
    List<UserDTO> getTop6UsersByMoney();
    boolean updateCashFlow(String username,int newMoney);
    long countUsers();
    int sumUserMoney();
    UserDTO getById(int id);
    List<UserDTO> getAllUser();

    boolean updateUserByid(int id,String level,int money,int banned,String password);
}
