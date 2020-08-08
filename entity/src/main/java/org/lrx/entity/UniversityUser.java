package org.lrx.entity;

public class UniversityUser {
    private String userId;
    private String username;
    private String password;
    private String userPicture;

    @Override
    public String toString() {
        return "UniversityUser{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userPicture='" + userPicture + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public UniversityUser(String userId, String username, String password, String userPicture) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userPicture = userPicture;
    }

    public UniversityUser() {
    }
}
