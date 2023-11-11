package com.example.demo;

class Gestors implements User {
    private String login;
    private String password;
    private String email;
    private String name;
    private String state;
    private UserType userType = UserType.CLIENT;

    public Gestors(String login, String password, String email, String name, String state, UserType type) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.name = name;
        this.state = state;
        this.userType = type;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setState(String state) {
        this.state = state;
    }

    private void setEmail(String email) {
        this.email = email;
    }

     @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public UserType getUserType() {
        return userType;
    }

    @Override
    public boolean canModify(User loggedInUser) {
        return loggedInUser != null && loggedInUser.getLogin().equals(this.getLogin());
    }
}
