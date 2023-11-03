package net.dev1m.shopgame.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "bank_auto")
public class Bank_auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "transaction_id")
    private String transaction_id;
    @Column(name = "amount")
    private int amount;
    @Column(name = "description")
    private String description;
    @Column(name = "time")
    private Date time;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users_bank_auto_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Users getUsers_bank_auto_id() {
        return users_bank_auto_id;
    }

    public void setUsers_bank_auto_id(Users users_bank_auto_id) {
        this.users_bank_auto_id = users_bank_auto_id;
    }
}
