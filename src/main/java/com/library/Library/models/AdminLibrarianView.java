package com.library.Library.models;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class AdminLibrarianView {
    private Long libraryId;
    private String userName;
    private String librarianId;
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
    private String libraryName;
}
