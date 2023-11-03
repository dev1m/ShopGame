package net.dev1m.shopgame.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups group_account_id;

    public Groups getGroup_account_id() {
        return group_account_id;
    }

    public void setGroup_account_id(Groups group_account_id) {
        this.group_account_id = group_account_id;
    }

    @Column(name = "username_acc")
    private String username_acc;
    @Column(name = "password_acc")
    private String password_acc;
    @Column(name = "detail")
    private String detail;
    @Column(name = "date_submit")
    private Date date_submit;
    @Column(name = "date_sell")
    private Date date_sell;
    @Column(name = "img")
    private String img;
    @Column(name = "money")
    private int money;

    @Column(name = "list_img")
    private String listImg;

    public String getListImg() {
        return listImg;
    }

    public void setListImg(String listImg) {
        this.listImg = listImg;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users_account_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDate_sell() {
        return date_sell;
    }

    public void setDate_sell(Date date_sell) {
        this.date_sell = date_sell;
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

    public Users getUsers_account_id() {
        return users_account_id;
    }

    public void setUsers_account_id(Users users_account_id) {
        this.users_account_id = users_account_id;
    }
}
