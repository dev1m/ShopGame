package net.dev1m.shopgame.service;

import net.dev1m.shopgame.entity.Bank_auto;
import net.dev1m.shopgame.entity.Banks;
import net.dev1m.shopgame.entity.Category_caythue;
import net.dev1m.shopgame.reponsitory.BankReponsitory;
import net.dev1m.shopgame.service.imp.BankServiceImp;
import net.dev1m.shopgame.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class BankService implements BankServiceImp {
    @Autowired
    BankReponsitory bankReponsitory;
    @Autowired
    FileServiceImp fileServiceImp;
    @Override
    public boolean insertBank(String bank_number,String bank_name, String bank_fullname, MultipartFile bank_logo, String bank_note) {
        boolean isIsertSuccess = false;
        boolean isSaveFileSuccess = fileServiceImp.savefile(bank_logo);
        if(isSaveFileSuccess){
            Banks banks = new Banks();
            banks.setBank_number(bank_number);
            banks.setBank_name(bank_name);
            banks.setBank_fullname(bank_fullname);
            banks.setBank_logo(bank_logo.getOriginalFilename());
            banks.setBank_note(bank_note);
            bankReponsitory.save(banks);
            isIsertSuccess = true;
        }
        return isIsertSuccess;
    }

    @Override
    public List<Banks> findAll() {
        return bankReponsitory.findAll();
    }

    @Override
    public boolean deleteById(int id) {
        try {
            bankReponsitory.deleteById(id);
            return true; // Trả về true nếu xóa thành công
        } catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateBank(int id, String bank_number, String bank_name, String bank_fullname, MultipartFile bank_logo, String bank_note) {
        Optional<Banks> banks = bankReponsitory.findById(id);
        if(banks.isPresent()){
            Banks banks1 = banks.get();
            banks1.setBank_number(bank_number);
            banks1.setBank_name(bank_name);
            banks1.setBank_fullname(bank_fullname);
            if(bank_logo != null){
                boolean isSaveFile = fileServiceImp.savefile(bank_logo);
                if(isSaveFile) {
                    banks1.setBank_logo(bank_logo.getOriginalFilename());
                }
            }else{
                banks1.setBank_logo(banks1.getBank_logo());
            }
            banks1.setBank_note(bank_note);
            bankReponsitory.save(banks1);
            return true;
        }
        else {
            return false;
        }
    }
}
