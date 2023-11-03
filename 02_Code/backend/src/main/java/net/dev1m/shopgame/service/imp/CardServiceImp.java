package net.dev1m.shopgame.service.imp;

import net.dev1m.shopgame.entity.Cards;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CardServiceImp {
    boolean updateCard(int id, int card_real_price,String card_status,String card_note);
    Cards findCard(String card_code);
    boolean insertCard(String card_code, String card_type, int card_price, int card_real_price, String card_seri,String card_pin,String card_status,String card_note,int user_id );
    List<Cards> getCardsByUserId(int userId);
}
