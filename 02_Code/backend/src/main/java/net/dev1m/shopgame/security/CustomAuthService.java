package net.dev1m.shopgame.security;

import net.dev1m.shopgame.entity.Users;
import net.dev1m.shopgame.reponsitory.AuthReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CustomAuthService implements UserDetailsService {
    @Autowired
    AuthReponsitory authReponsitory;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = authReponsitory.findByUsername(username);
        if(users == null){
            throw new  UsernameNotFoundException("User ko tồn tại");
        }

        return new User(username,users.getPassword(),new ArrayList<>());
    }
}
