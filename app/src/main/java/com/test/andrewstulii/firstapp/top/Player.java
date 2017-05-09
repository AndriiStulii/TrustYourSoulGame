package com.test.andrewstulii.firstapp.top;

/**
 * Created by andrewstulii.
 */
public class Player {

    private String userName;
    private Integer userResult;

    public Player(String userName, int userResult) {
        this.userName = userName;
        this.userResult = userResult;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserResult() {
        return userResult;
    }

    public void setUserResult(Integer userResult) {
        this.userResult = userResult;
    }
}
