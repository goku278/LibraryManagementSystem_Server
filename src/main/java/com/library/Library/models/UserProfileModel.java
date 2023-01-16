package com.library.Library.models;
import lombok.Data;
import lombok.ToString;
@ToString
@Data
public class UserProfileModel {
    private String userName;
    private String email;
    private String role;
    private String contactNo;
    private Long userId;

    public UserProfileModel() {
    }

    public UserProfileModel(String userName) {
        this.userName = userName;
    }
}