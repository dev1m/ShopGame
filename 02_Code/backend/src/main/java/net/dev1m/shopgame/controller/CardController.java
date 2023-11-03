package net.dev1m.shopgame.controller;

import net.dev1m.shopgame.dto.AccountDTO;
import net.dev1m.shopgame.entity.Cards;
import net.dev1m.shopgame.payLoad.ResponseData;
import net.dev1m.shopgame.service.imp.CardServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardServiceImp cardServiceImp;

    @PostMapping("/addCard")
    public ResponseEntity<?> createCard(
            @RequestParam String card_code
            ,@RequestParam String card_type
            ,@RequestParam int card_price
            ,@RequestParam int card_real_price
            ,@RequestParam String card_seri
            ,@RequestParam String card_pin
            ,@RequestParam String card_status
            ,@RequestParam String card_note
            ,@RequestParam int userId)
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = cardServiceImp.insertCard(card_code,card_type,card_price,card_real_price,card_seri,card_pin,card_status,card_note,userId);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/get/{card_code}")
    public ResponseEntity<?> findCardByCardCode(@PathVariable String card_code) {
        ResponseData responseData = new ResponseData();
        responseData.setData(cardServiceImp.findCard(card_code));
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateAccount(
            @RequestParam int id,
            @RequestParam(value = "card_real_price", required = false) int card_real_price,
            @RequestParam(value = "card_status", required = false) String card_status,
            @RequestParam(value = "card_note", required = false) String card_note
    )
    {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = cardServiceImp.updateCard(id,card_real_price,card_status,card_note);
        responseData.setData(isSuccess);
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/getCardByUserId/{userId}")
    public ResponseEntity<?> getCardsByUserId(@PathVariable int userId) {
        ResponseData responseData = new ResponseData();
        List<Cards> cards = cardServiceImp.getCardsByUserId(userId);
        if (!cards.isEmpty()) {
            responseData.setData(cards);
        } else {
            responseData.setData(false);
            responseData.setSuccess(false);
        }
        return new  ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
