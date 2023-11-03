package net.dev1m.shopgame.reponsitory;

import net.dev1m.shopgame.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface UserReponsitory extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);
    List<Users> findTop6ByOrderByMoneyDesc();
    @Query("SELECT SUM(u.money) FROM users u")
    int sumUserMoney();
}
