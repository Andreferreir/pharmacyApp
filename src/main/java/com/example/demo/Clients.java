package com.example.demo;

public class Clients implements User{
    private String login;
    private String password;
    private String email;
    private String name;
    private String state;
    private UserType userType = UserType.CLIENT;
    long nif;
    long tlf;

    public Clients(String login, String password, String email, String name, String state, UserType type, long nif,  long tlf) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.name = name;
        this.state = state;
        this.userType = type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
            return password;
    }

    public String getName() {
        return name;
    }


    public String getState() {
        return state;
    }


    public String getEmail() {
        return email;
    }


    public UserType getUserType() {
        return userType;
    }


    public boolean canModify(User loggedInUser) {
        return loggedInUser != null && loggedInUser.getLogin().equals(this.getLogin());
    }
}
