package net.dev1m.shopgame.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "groups")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "title")
    private String title;
    @Column(name = "display")
    private String display;
    @Column(name = "img")
    private String img;
    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "group_account_id")
    private Set<Accounts> listAccountGroup;

    public Set<Accounts> getListAccountGroup() {
        return listAccountGroup;
    }

    public void setListAccountGroup(Set<Accounts> listAccountGroup) {
        this.listAccountGroup = listAccountGroup;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
