package com.library.Library.models;

import lombok.Data;
import lombok.ToString;
@ToString
@Data
public class UserModel {
    private String userName;
    private String email;
    private String role;
    private String contactNo;
    private String userId;
}