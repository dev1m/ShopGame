package net.dev1m.shopgame.dto;
public class UserDTO {
    private int id;
    private String username;

    private String password;

    private int money;

    private String level;

    private String email;
    private int banned;

    public UserDTO() {

    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
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

    public UserDTO(int id, String username, String password, int money, String level, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.money = money;
        this.level = level;
        this.email = email;
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
