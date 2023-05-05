package kz.bitlab.models;

public class Users {
    private int id;
    private String email;
    private String password;
    private String full_name;
    private String role;

    public Users() {
    }

    public Users(int id, String email, String password, String full_name, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.full_name = full_name;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(int role_id) {
        this.role = role;
    }
}
