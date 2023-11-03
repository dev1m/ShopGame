package net.dev1m.shopgame.dto;

import java.util.Date;

public class AccountDTO {
    private int id;
    private int group_id;
    private String username_acc;
    private String password_acc;
    private String detail;
    private Date date_submit;
    private Date date_sell;

    public Date getDate_sell() {
        return date_sell;
    }

    public void setDate_sell(Date date_sell) {
        this.date_sell = date_sell;
    }

    private String img;
    private int money;
    private String list_img;
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getUsername_acc() {
        return username_acc;
    }

    public void setUsername_acc(String username_acc) {
        this.username_acc = username_acc;
    }

    public String getPassword_acc() {
        return password_acc;
    }

    public void setPassword_acc(String password_acc) {
        this.password_acc = password_acc;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getDate_submit() {
        return date_submit;
    }

    public void setDate_submit(Date date_submit) {
        this.date_submit = date_submit;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getList_img() {
        return list_img;
    }

    public void setList_img(String list_img) {
        this.list_img = list_img;
    }
}
