package net.dev1m.shopgame.service;

import net.dev1m.shopgame.dto.UserDTO;
import net.dev1m.shopgame.entity.Users;
import net.dev1m.shopgame.reponsitory.UserReponsitory;
import net.dev1m.shopgame.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    UserReponsitory userReponsitory;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDTO getUserByUsername(String username) {
        Users user = userReponsitory.findByUsername(username);
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setEmail(user.getEmail());
            userDTO.setLevel(user.getLevel());
            userDTO.setMoney(user.getMoney());
            userDTO.setBanned(user.getBanned());
            return userDTO;
        } else {
            return null; // Xử lý trường hợp không tìm thấy người dùng
        }
    }

    @Override
    public List<UserDTO> getTop6UsersByMoney() {
        List<Users> users = userReponsitory.findTop6ByOrderByMoneyDesc();
        List<UserDTO> userDTOs = new ArrayList<>();

        for (Users user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setMoney(user.getMoney());
            userDTOs.add(userDTO);
        }

        return userDTOs;
    }

    @Override
    public boolean updateCashFlow(String username, int newMoney) {
        boolean isSuccess = false;
        Users user = userReponsitory.findByUsername(username);
        if (user != null) {
            user.setMoney(newMoney);
            userReponsitory.save(user);
            isSuccess = true;
        }
        return isSuccess;
    }

    @Override
    public long countUsers() {
        return userReponsitory.count();
    }

    @Override
    public int sumUserMoney() {
        return userReponsitory.sumUserMoney();
    }

    @Override
    public UserDTO getById(int id) {
        Optional<Users> user = userReponsitory.findById(id);
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.get().getId());
            userDTO.setUsername(user.get().getUsername());
            userDTO.setPassword(user.get().getPassword());
            userDTO.setEmail(user.get().getEmail());
            userDTO.setLevel(user.get().getLevel());
            userDTO.setMoney(user.get().getMoney());
            userDTO.setBanned(user.get().getBanned());
            return userDTO;
        } else {
            return null; // Xử lý trường hợp không tìm thấy người dùng
        }
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<Users> users = userReponsitory.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (Users data: users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(data.getId());
            userDTO.setUsername(data.getUsername());
            userDTO.setPassword(data.getPassword());
            userDTO.setEmail(data.getEmail());
            userDTO.setMoney(data.getMoney());
            userDTO.setLevel(data.getLevel());
            userDTO.setBanned(data.getBanned());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public boolean updateUserByid(int id, String level, int money, int banned, String password) {
        Optional<Users> users = userReponsitory.findById(id);
        if(users.isPresent()){
            Users users1 = users.get();
            users1.setPassword(this.passwordEncoder.encode(password));
            users1.setMoney(money);
            users1.setBanned(banned);
            users1.setLevel(level);
            userReponsitory.save(users1);
            return true;
        }
        else {
            return false;
        }


    }
}
