package net.dev1m.shopgame.reponsitory;

import net.dev1m.shopgame.entity.Accounts;
import net.dev1m.shopgame.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface CardReponsitory extends JpaRepository<Cards, Integer> {
    @Query("SELECT c FROM cards c WHERE c.card_code = :card_code AND c.card_status = 'xuly'")
    Cards findCard(String card_code);

    @Query("SELECT c FROM cards c WHERE c.users_card_id.id = :userId")
    List<Cards> findCardsByUserId(int userId);
}
