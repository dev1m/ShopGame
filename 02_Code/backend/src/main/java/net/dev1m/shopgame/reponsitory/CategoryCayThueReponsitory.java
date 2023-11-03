package net.dev1m.shopgame.reponsitory;

import net.dev1m.shopgame.entity.Category_caythue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface CategoryCayThueReponsitory extends JpaRepository<Category_caythue,Integer> {
    Category_caythue findById(int categoryId);
}
