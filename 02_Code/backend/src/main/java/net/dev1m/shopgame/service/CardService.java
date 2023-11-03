package net.dev1m.shopgame.service;

import net.dev1m.shopgame.entity.Cards;
import net.dev1m.shopgame.entity.Users;
import net.dev1m.shopgame.reponsitory.CardReponsitory;
import net.dev1m.shopgame.service.imp.CardServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CardService implements CardServiceImp {
    @Autowired
    CardReponsitory cardReponsitory;

    @Override
    public boolean updateCard(int id, int card_real_price, String card_status,String card_note) {
        Optional<Cards> cards = cardReponsitory.findById(id);
        if(cards.isPresent()){
            Cards cards1 = cards.get();
            cards1.setCard_real_price(card_real_price);
            cards1.setCard_real_price(card_real_price);
            cards1.setCard_note(card_note);
            cardReponsitory.save(cards1);
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public Cards findCard(String card_code) {
        Cards cards = cardReponsitory.findCard(card_code);
        if(cards != null){
            return cards;
        }else {
            return null;
        }
    }

    @Override
    public boolean insertCard(String card_code, String card_type, int card_price, int card_real_price, String card_seri, String card_pin, String card_status, String card_note, int user_id) {
        boolean isInsert = false;
        try {
            Cards cards = new Cards();
            cards.setCard_code(card_code);
            cards.setCard_type(card_type);
            cards.setCard_price(card_price);
            cards.setCard_real_price(card_real_price);
            cards.setCard_seri(card_seri);
            cards.setCard_pin(card_pin);
            cards.setCard_status(card_status);
            cards.setCard_note(card_note);
            Date currentDate = new Date();
            Timestamp timestamp = new Timestamp(currentDate.getTime());
            cards.setCreate_date(timestamp);
            Users users = new Users();
            users.setId(user_id);
            cards.setUsers_card_id(users);
            isInsert = true;
        }catch (Exception e){
            System.out.println("Error insert card "+e.getMessage());
        }
        return isInsert;
    }

    @Override
    public List<Cards> getCardsByUserId(int userId) {
        List<Cards> cards = cardReponsitory.findCardsByUserId(userId);
        if(cards != null){
            return cards;
        }
        return null;
    }
}
