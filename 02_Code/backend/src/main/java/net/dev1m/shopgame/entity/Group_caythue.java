package net.dev1m.shopgame.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "group_caythue")
public class Group_caythue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "display")
    private String display;
    @Column(name = "money")
    private int money;
    @ManyToOne
    @JoinColumn(name = "category_caythue_id")
    private Category_caythue categoryCaythue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @OneToMany(mappedBy = "group_caythue_id")
    private Set<Order_caythue> listOrderCayThue;

    public Set<Order_caythue> getListOrderCayThue() {
        return listOrderCayThue;
    }

    public void setListOrderCayThue(Set<Order_caythue> listOrderCayThue) {
        this.listOrderCayThue = listOrderCayThue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Category_caythue getCategoryCaythue() {
        return categoryCaythue;
    }

    public void setCategoryCaythue(Category_caythue categoryCaythue) {
        this.categoryCaythue = categoryCaythue;
    }
}
