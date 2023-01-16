package com.library.Library.models;

import lombok.Data;
import lombok.ToString;

import java.util.Arrays;

@Data
@ToString
public class UsersRegisterModel {
    private String userName;
    private String email;
    private String role;
    private String qualification;
    private String resident;
    private String pincode;
    private String district;
    private String state;
    private String houseNo;
    private String landMark;
    private String contactNo;

    @Override
    public String toString() {
        return "UsersRegisterModel{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", qualification='" + qualification + '\'' +
                ", resident='" + resident + '\'' +
                ", pincode='" + pincode + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", houseNo='" + houseNo + '\'' +
                ", landMark='" + landMark + '\'' +
                ", contactNo=" + contactNo +
                '}';
    }
}