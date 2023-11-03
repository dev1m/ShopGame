package net.dev1m.shopgame.reponsitory;

import net.dev1m.shopgame.entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface GroupGameReponsitory extends JpaRepository<Groups, Integer> {
    Optional<List<Groups>> findByCategory_Id(int id);
    int countByCategoryIdAndDisplay(int id, String display);
    Groups  findById(int id);

}
