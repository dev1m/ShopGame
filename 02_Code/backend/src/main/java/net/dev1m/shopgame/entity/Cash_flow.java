package net.dev1m.shopgame.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "cash_flow")
public class Cash_flow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cash_old")
    private int cash_old;
    @Column(name = "cash_change")
    private int cash_change;
    @Column(name = "cash_new")
    private int cash_new;
    @Column(name = "time")
    private Date time;
    @Column(name = "cash_note")
    private String cash_note;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users_cash_flow;

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

    public Users getUsers_cash_flow() {
        return users_cash_flow;
    }

    public void setUsers_cash_flow(Users users_cash_flow) {
        this.users_cash_flow = users_cash_flow;
    }
}
