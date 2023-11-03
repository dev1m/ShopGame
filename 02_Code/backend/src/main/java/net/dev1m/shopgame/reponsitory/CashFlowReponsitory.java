package net.dev1m.shopgame.reponsitory;


import net.dev1m.shopgame.entity.Cash_flow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface CashFlowReponsitory extends JpaRepository<Cash_flow,Integer> {
    @Query("SELECT c FROM cash_flow c WHERE c.users_cash_flow.id = :userId")
    List<Cash_flow> findCash_FlowByUserId(int userId);


}
