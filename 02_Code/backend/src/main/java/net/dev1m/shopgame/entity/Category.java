package net.dev1m.shopgame.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "display")
    private String display;
    @Column(name = "img")
    private String img;

    @OneToMany(mappedBy = "category")
    private Set<Groups> listGroup;

    public Set<Groups> getListGroup() {
        return listGroup;
    }

    public void setListGroup(Set<Groups> listGroup) {
        this.listGroup = listGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
