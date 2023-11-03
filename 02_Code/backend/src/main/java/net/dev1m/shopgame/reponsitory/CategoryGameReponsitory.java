package net.dev1m.shopgame.reponsitory;

import net.dev1m.shopgame.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface CategoryGameReponsitory extends JpaRepository<Category,Integer> {

}
