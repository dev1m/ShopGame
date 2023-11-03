package net.dev1m.shopgame.entity;

import javax.persistence.*;

@Entity(name = "order_caythue")
public class Order_caythue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "money")
    private int money;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private String status;
    @Column(name = "note_user")
    private String note_user;
    @Column(name = "note_admin")
    private String note_admin;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @ManyToOne
    @JoinColumn(name = "group_caythue_id")
    private Group_caythue group_caythue_id;

    public Group_caythue getGroup_caythue_id() {
        return group_caythue_id;
    }

    public void setGroup_caythue_id(Group_caythue group_caythue_id) {
        this.group_caythue_id = group_caythue_id;
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
