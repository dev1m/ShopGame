package net.dev1m.shopgame.service.imp;

import net.dev1m.shopgame.dto.UserDTO;


import java.util.List;

public interface AuthServiceImp {
    String checkLogin(String username,String password);
    String register(UserDTO userDTO);

    boolean changePassword(String username,String newPassword);
    boolean resetPasswordAndSendEmail(String email);


}
