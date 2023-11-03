package net.dev1m.shopgame.reponsitory;

import net.dev1m.shopgame.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface OptionReponsitory extends JpaRepository<Options,Integer> {
    @Query("SELECT o FROM options o WHERE o.option_key = :key")
    Options findByOptionKey(@Param("key") String key);
}
