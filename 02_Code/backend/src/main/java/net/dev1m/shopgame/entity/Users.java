package net.dev1m.shopgame.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "money")
    private int money;
    @Column(name = "level")
    private String level;
    @Column(name = "email")
    private String email;
    @Column(name = "banned")
    private int banned;

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    @OneToMany(mappedBy = "users")
    private Set<Order_caythue> listOrderCayThue;

    public Set<Order_caythue> getListOrderCayThue() {
        return listOrderCayThue;
    }

    public void setListOrderCayThue(Set<Order_caythue> listOrderCayThue) {
        this.listOrderCayThue = listOrderCayThue;
    }

    @OneToMany(mappedBy = "users_cash_flow")
    private Set<Cash_flow> listCashFlow;

    public Set<Cash_flow> getListCashFlow() {
        return listCashFlow;
    }

    public void setListCashFlow(Set<Cash_flow> listCashFlow) {
        this.listCashFlow = listCashFlow;
    }

    @OneToMany(mappedBy = "users_card_id")
    private Set<Cards> listCard;

    public Set<Cards> getListCard() {
        return listCard;
    }

    public void setListCard(Set<Cards> listCard) {
        this.listCard = listCard;
    }

    @OneToMany(mappedBy = "users_bank_auto_id")
    private Set<Bank_auto> listBankAuto;

    public Set<Bank_auto> getListBankAuto() {
        return listBankAuto;
    }

    public void setListBankAuto(Set<Bank_auto> listBankAuto) {
        this.listBankAuto = listBankAuto;
    }

    @OneToMany(mappedBy = "users_account_id")
    private Set<Accounts> listAccountUser;

    public Set<Accounts> getListAccountUser() {
        return listAccountUser;
    }

    public void setListAccountUser(Set<Accounts> listAccountUser) {
        this.listAccountUser = listAccountUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
