package net.dev1m.shopgame.dto;

public class GroupCayThueDTO {
    private int id;
    private int category_caythue_id;

    private String title;

    private String display;

    private int money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_caythue_id() {
        return category_caythue_id;
    }

    public void setCategory_caythue_id(int category_caythue_id) {
        this.category_caythue_id = category_caythue_id;
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
}
