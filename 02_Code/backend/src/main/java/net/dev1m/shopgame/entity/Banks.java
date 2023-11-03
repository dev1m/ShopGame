package net.dev1m.shopgame.entity;

import javax.persistence.*;

@Entity(name = "banks")
public class Banks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "bank_number")
    private String bank_number;
    @Column(name = "bank_name")
    private String bank_name;

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    @Column(name = "bank_fullname")
    private String bank_fullname;
    @Column(name = "bank_logo")
    private String bank_logo;
    @Column(name = "bank_note")
    private String bank_note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBank_number() {
        return bank_number;
    }

    public void setBank_number(String bank_number) {
        this.bank_number = bank_number;
    }

    public String getBank_fullname() {
        return bank_fullname;
    }

    public void setBank_fullname(String bank_fullname) {
        this.bank_fullname = bank_fullname;
    }

    public String getBank_logo() {
        return bank_logo;
    }

    public void setBank_logo(String bank_logo) {
        this.bank_logo = bank_logo;
    }

    public String getBank_note() {
        return bank_note;
    }

    public void setBank_note(String bank_note) {
        this.bank_note = bank_note;
    }
}
