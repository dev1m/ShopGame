package net.dev1m.shopgame.dto;
import java.util.Date;

public class CashFlowDTO {
    private int id;
    private int cash_old;
    private int cash_change;
    private int cash_new;
    private Date time;
    private String cash_note;
    private int user_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCash_old() {
        return cash_old;
    }

    public void setCash_old(int cash_old) {
        this.cash_old = cash_old;
    }

    public int getCash_change() {
        return cash_change;
    }

    public void setCash_change(int cash_change) {
        this.cash_change = cash_change;
    }

    public int getCash_new() {
        return cash_new;
    }

    public void setCash_new(int cash_new) {
        this.cash_new = cash_new;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCash_note() {
        return cash_note;
    }

    public void setCash_note(String cash_note) {
        this.cash_note = cash_note;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
