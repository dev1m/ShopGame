package net.dev1m.shopgame.reponsitory;

import net.dev1m.shopgame.entity.Group_caythue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@EnableJpaRepositories
@Repository
public interface GroupCayThueReponsitory extends JpaRepository<Group_caythue,Integer> {
    List<Group_caythue> findByCategoryCaythueId(int id);
    int countByCategoryCaythueIdAndDisplay(int id, String display);
    Group_caythue findById(int id);


}
