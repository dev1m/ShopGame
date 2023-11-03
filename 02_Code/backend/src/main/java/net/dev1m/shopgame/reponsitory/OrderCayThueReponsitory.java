package net.dev1m.shopgame.reponsitory;

import net.dev1m.shopgame.entity.Order_caythue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface OrderCayThueReponsitory extends JpaRepository<Order_caythue,Integer> {
    @Query("SELECT o FROM order_caythue o WHERE o.users.id = :userId")
    List<Order_caythue> findOrderCayThueByUserId(int userId);

    @Query("SELECT COUNT(o) FROM order_caythue o WHERE o.status = 'xuly'")
    int countOrdersWithStatusXuly();
}
