package net.dev1m.shopgame.reponsitory;

import net.dev1m.shopgame.entity.Accounts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface AccountReponsitory extends JpaRepository<Accounts,Integer> {
    @Query("SELECT a FROM accounts a WHERE a.group_account_id.id = :groupId")
    List<Accounts> findByGroup_Id(int groupId);

    @Query("SELECT COUNT(a) FROM accounts a WHERE a.group_account_id.id = :groupId AND a.users_account_id = NULL")
    int countAccountsByGroupId(int groupId);

    Accounts findById(int id);
    @Query("SELECT a FROM accounts a WHERE a.users_account_id.id = :userId")
    List<Accounts> findAccountsByUserId(int userId);

    @Query("SELECT COUNT(*) FROM accounts  WHERE user_id IS NOT NULL")
    long countAccountsByUserIdIsNotNull();

    @Query("SELECT COUNT(*) FROM accounts WHERE user_id IS NULL")
    long countAccountsByUserIdIsNull();

    @Query("SELECT SUM(money) FROM accounts WHERE user_id IS NOT NULL")
    Integer sumMoneySell();

    @Query("SELECT a FROM accounts a WHERE user_id IS NOT NULL")
    List<Accounts> findAccountSold();

    @Query("SELECT a FROM accounts a WHERE a.group_account_id.id = :groupId AND a.users_account_id.id = NULL")
    Page<Accounts> findByGroup_Account_Id(int groupId, Pageable pageable);


}
