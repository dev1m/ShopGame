package net.dev1m.shopgame.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "cards")
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String getCard_code() {
        return card_code;
    }

    public void setCard_code(String card_code) {
        this.card_code = card_code;
    }

    @Column(name = "card_code")
    private String card_code;
    @Column(name = "card_type")
    private String card_type;
    @Column(name = "card_price")
    private int card_price;
    @Column(name = "card_real_price")
    private int card_real_price;
    @Column(name = "card_seri")
    private String card_seri;
    @Column(name = "card_pin")
    private String  card_pin;
    @Column(name = "create_date")
    private Date create_date;
    @Column(name = "card_status")
    private String card_status;
    @Column(name = "card_note")
    private String card_note;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users_card_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public int getCard_price() {
        return card_price;
    }

    public void setCard_price(int card_price) {
        this.card_price = card_price;
    }

    public int getCard_real_price() {
        return card_real_price;
    }

    public void setCard_real_price(int card_real_price) {
        this.card_real_price = card_real_price;
    }

    public String getCard_seri() {
        return card_seri;
    }

    public void setCard_seri(String card_seri) {
        this.card_seri = card_seri;
    }

    public String getCard_pin() {
        return card_pin;
    }

    public void setCard_pin(String card_pin) {
        this.card_pin = card_pin;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getCard_status() {
        return card_status;
    }

    public void setCard_status(String card_status) {
        this.card_status = card_status;
    }

    public String getCard_note() {
        return card_note;
    }

    public void setCard_note(String card_note) {
        this.card_note = card_note;
    }

    public Users getUsers_card_id() {
        return users_card_id;
    }

    public void setUsers_card_id(Users users_card_id) {
        this.users_card_id = users_card_id;
    }
}
