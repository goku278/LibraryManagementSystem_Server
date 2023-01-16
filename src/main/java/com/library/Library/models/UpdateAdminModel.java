package com.library.Library.models;
import lombok.Data;
import lombok.ToString;
@ToString
@Data
public class UpdateAdminModel {
    private String adminName;
    private String email;
    private String contactNo;
}