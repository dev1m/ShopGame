package net.dev1m.shopgame.dto;

import net.dev1m.shopgame.entity.Group_caythue;
import net.dev1m.shopgame.entity.Users;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class OrderCayThueDTO {
    private int id;

    private int money;

    private String username;

    private String password;

    private String status;

    private String note_user;

    private String note_admin;

    private int user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote_user() {
        return note_user;
    }

    public void setNote_user(String note_user) {
        this.note_user = note_user;
    }

    public String getNote_admin() {
        return note_admin;
    }

    public void setNote_admin(String note_admin) {
        this.note_admin = note_admin;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGroup_caythue_id() {
        return group_caythue_id;
    }

    public void setGroup_caythue_id(int group_caythue_id) {
        this.group_caythue_id = group_caythue_id;
    }

    private int group_caythue_id;
}
