package net.dev1m.shopgame.reponsitory;

import net.dev1m.shopgame.entity.Banks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface BankReponsitory  extends JpaRepository<Banks,Integer> {
}
