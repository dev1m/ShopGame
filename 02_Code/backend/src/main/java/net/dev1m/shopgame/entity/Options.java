package net.dev1m.shopgame.entity;

import javax.persistence.*;

@Entity(name = "options")
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(name = "option_key")
    private String option_key;
    @Column(name = "option_value")
    private String option_value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOption_key() {
        return option_key;
    }

    public void setOption_key(String option_key) {
        this.option_key = option_key;
    }

    public String getOption_value() {
        return option_value;
    }

    public void setOption_value(String option_value) {
        this.option_value = option_value;
    }
}
