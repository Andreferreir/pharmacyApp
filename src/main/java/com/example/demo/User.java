package com.example.demo;

interface User {
    String getLogin();
    String getPassword();
    String getName();
    String getState();
    String getEmail();
    UserType getUserType();
    boolean canModify(User loggedInUser);
}
