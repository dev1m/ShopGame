package net.dev1m.shopgame.service;

import net.dev1m.shopgame.dto.AccountDTO;
import net.dev1m.shopgame.entity.Accounts;
import net.dev1m.shopgame.entity.Groups;
import net.dev1m.shopgame.entity.Users;
import net.dev1m.shopgame.reponsitory.AccountReponsitory;
import net.dev1m.shopgame.reponsitory.UserReponsitory;
import net.dev1m.shopgame.service.imp.AccountServiceImp;
import net.dev1m.shopgame.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.sql.Timestamp;
import java.util.*;

@Service
public class AccountService implements AccountServiceImp {
    @Autowired
    FileServiceImp fileServiceImp;
    @Autowired
    AccountReponsitory accountReponsitory;
    @Autowired
    UserReponsitory userReponsitory;

    @Override
    public boolean addAccount(int group_id, String username_acc, String password_acc, String detail,  MultipartFile img, int money, MultipartFile[] list_img) {
        boolean isIsertSuccess = false;
        try {
            boolean isSaveFileSuccess = fileServiceImp.savefile(img);
            if(isSaveFileSuccess) {
                Accounts account = new Accounts();
                Groups groups = new Groups();
                groups.setId(group_id);
                account.setGroup_account_id(groups);
                account.setUsername_acc(username_acc);
                account.setPassword_acc(password_acc);
                account.setDetail(detail);
                Date currentDate = new Date();
                Timestamp timestamp = new Timestamp(currentDate.getTime());
                account.setDate_submit(timestamp);
                account.setImg(img.getOriginalFilename());
                account.setMoney(money);
                try {
                    List<String> fileNames = new ArrayList<>();
                    Arrays.asList(list_img).stream().forEach(file -> {
                        fileServiceImp.savefile(file);
                        fileNames.add(file.getOriginalFilename());
                    });
                    String imageNamesStr = String.join(",", fileNames);
                    account.setListImg(imageNamesStr);
                }catch (Exception e){
                    System.out.println("Error Upload Images "+ e.getMessage());
                }
                accountReponsitory.save(account);
                isIsertSuccess = true;
            }
        }catch (Exception e){
            System.out.println("Error insert group "+e.getMessage());
        }
        return isIsertSuccess ;
    }

    @Override
    public List<AccountDTO> getAccountsByGroupId(int groupId) {
        List<Accounts> accountsList = accountReponsitory.findByGroup_Id(groupId);
        List<AccountDTO> accountDTOList = new ArrayList<>();
        for (Accounts account : accountsList) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setId(account.getId());
            Integer userId = (account.getUsers_account_id() != null) ? account.getUsers_account_id().getId() : -1;
            accountDTO.setUser_id(userId);
            accountDTO.setDetail(account.getDetail());
            accountDTO.setImg(account.getImg());
            accountDTO.setUsername_acc(account.getUsername_acc());
            accountDTO.setDate_submit(account.getDate_submit());
            accountDTO.setMoney(account.getMoney());
            accountDTOList.add(accountDTO);
        }
        return accountDTOList;
    }


    @Override
    public int countAccountsByGroupId(int groupId) {
        return accountReponsitory.countAccountsByGroupId(groupId);
    }

    @Override
    public AccountDTO getAccountById(int id) {
        Accounts accounts = accountReponsitory.findById(id);
        if (accounts != null) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setId(accounts.getId());
            accountDTO.setGroup_id(accounts.getGroup_account_id().getId());
            accountDTO.setUsername_acc(accounts.getUsername_acc());
            accountDTO.setPassword_acc(accounts.getPassword_acc());
            accountDTO.setImg(accounts.getImg());
            accountDTO.setDetail(accounts.getDetail());
            accountDTO.setMoney(accounts.getMoney());
            accountDTO.setList_img(accounts.getListImg());
            accountDTO.setDate_sell(accounts.getDate_sell());
            Integer userId = (accounts.getUsers_account_id() != null) ? accounts.getUsers_account_id().getId() : -1;
            // Gán giá trị mặc định -1 là null;
            accountDTO.setUser_id(userId);
            return accountDTO;

        } else {
            return null; // Xử lý trường hợp không tìm thấy người dùng
        }
    }

    @Override
    public boolean orderAccount(int id, int user_id) {
        Accounts accounts = accountReponsitory.findById(id);
        if(accounts != null){
            Users users = new Users();
            users.setId(user_id);
            accounts.setUsers_account_id(users);
            Date currentDate = new Date();
            Timestamp timestamp = new Timestamp(currentDate.getTime());
            accounts.setDate_sell(timestamp);
            accountReponsitory.save(accounts);
            return true;
        }else {
            return false; // Trả về false nếu không tìm thấy tài khoản
        }
    }

    @Override
    public List<AccountDTO> getAccountsByUserId(int userId) {
        List<Accounts> accounts = accountReponsitory.findAccountsByUserId(userId);
        List<AccountDTO> accountDTOs = new ArrayList<>();
        for (Accounts account : accounts) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setId(account.getId());
            accountDTO.setGroup_id(account.getGroup_account_id().getId());
            accountDTO.setUsername_acc(account.getUsername_acc());
            accountDTO.setPassword_acc(account.getPassword_acc());
            accountDTO.setMoney(account.getMoney());
            accountDTO.setDate_sell(account.getDate_sell());
            accountDTO.setUser_id(account.getUsers_account_id().getId());
            accountDTOs.add(accountDTO);
        }
        return accountDTOs;
    }

    @Override
    public long countAccountsByUserIdIsNull() {
        return accountReponsitory.countAccountsByUserIdIsNull();
    }

    @Override
    public long countAccountsByUserIdIsNotNull() {
        return accountReponsitory.countAccountsByUserIdIsNotNull();
    }

    @Override
    public Integer sumMoneySell() {
        return accountReponsitory.sumMoneySell();
    }

    @Override
    public List<AccountDTO> getAccountSold() {
        List<Accounts> accounts = accountReponsitory.findAccountSold();
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Accounts data: accounts) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setId(data.getId());
            accountDTO.setImg(data.getImg());
            accountDTO.setUsername_acc(data.getUsername_acc());
            accountDTO.setUser_id(data.getUsers_account_id().getId());
            accountDTO.setMoney(data.getMoney());
            accountDTO.setDate_submit(data.getDate_submit());
            accountDTO.setDate_sell(data.getDate_sell());
            accountDTOS.add(accountDTO);
        }
        return accountDTOS;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            accountReponsitory.deleteById(id);
            return true; // Trả về true nếu xóa thành công
        } catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateAccount(int id, String username_acc, String password_acc, String detail, MultipartFile img, int money, MultipartFile[] list_img) {
        Accounts accounts = accountReponsitory.findById(id);
        if(accounts != null){
            accounts.setUsername_acc(username_acc);
            accounts.setPassword_acc(password_acc);
            accounts.setDetail(detail);
            accounts.setMoney(money);
            if(img != null){
                boolean isSaveFile = fileServiceImp.savefile(img);
                if(isSaveFile) {
                    accounts.setImg(img.getOriginalFilename());
                }
            }else{
                accounts.setImg(accounts.getImg());
            }
            if(list_img != null){
                try {
                    List<String> fileNames = new ArrayList<>();
                    Arrays.asList(list_img).stream().forEach(file -> {
                        fileServiceImp.savefile(file);
                        fileNames.add(file.getOriginalFilename());
                    });
                    String imageNamesStr = String.join(",", fileNames);
                    accounts.setListImg(imageNamesStr);
                }catch (Exception e){
                    System.out.println("Error Upload Images "+ e.getMessage());
                }
            }
            else {
                accounts.setListImg(accounts.getListImg());
            }
            accountReponsitory.save(accounts);
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public Page<AccountDTO> getAccountsByGroupIdPaged(int groupId, Pageable pageable) {
        Page<Accounts> accountsPage = accountReponsitory.findByGroup_Account_Id(groupId, pageable);
        return accountsPage.map(account -> {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setId(account.getId());
            accountDTO.setDetail(account.getDetail());
            accountDTO.setImg(account.getImg());
            accountDTO.setUsername_acc(account.getUsername_acc());
            accountDTO.setDate_submit(account.getDate_submit());
            accountDTO.setMoney(account.getMoney());
            return accountDTO;
        });
    }


}
