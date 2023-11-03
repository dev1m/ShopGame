package net.dev1m.shopgame.service.imp;

import net.dev1m.shopgame.entity.Bank_auto;
import net.dev1m.shopgame.entity.Banks;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BankServiceImp {
    boolean insertBank(String bank_number,String bank_name, String bank_fullname, MultipartFile bank_logo, String bank_note);
    List<Banks> findAll();

    boolean deleteById(int id);
    boolean updateBank(int id,String bank_number,String bank_name, String bank_fullname, MultipartFile bank_logo, String bank_note);
}

