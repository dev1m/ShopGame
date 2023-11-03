package net.dev1m.shopgame.service.imp;

import net.dev1m.shopgame.dto.AccountDTO;
import net.dev1m.shopgame.entity.Accounts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface AccountServiceImp {
    boolean addAccount(int group_id, String username_acc, String password_acc, String detail, MultipartFile img,int money,MultipartFile[] list_img);
    List<AccountDTO> getAccountsByGroupId(int groupId);

    int countAccountsByGroupId(int groupId);

    AccountDTO getAccountById(int id);
    boolean orderAccount(int id,int user_id);
    List<AccountDTO> getAccountsByUserId(int userId);
    long countAccountsByUserIdIsNull();
    long countAccountsByUserIdIsNotNull();
    Integer sumMoneySell();

    List<AccountDTO> getAccountSold();

    boolean deleteById(int id);

    boolean updateAccount(int id, String username_acc, String password_acc, String detail, MultipartFile img,int money,MultipartFile[] list_img);
    Page<AccountDTO> getAccountsByGroupIdPaged(int groupId, Pageable pageable);


}
