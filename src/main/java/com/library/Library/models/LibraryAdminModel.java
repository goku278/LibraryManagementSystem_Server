package com.library.Library.models;

import lombok.Data;
import lombok.ToString;

//@ToString
//@Data
public class LibraryAdminModel {
    private String libraryName;
    private Long adminId;
    private Long userId;
    public String getLibraryName() {
        return libraryName;
    }
    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }
    public Long getAdminId() {
        return adminId;
    }
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}