package com.library.Library.db;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
@Table(name = "library_admin")
@Entity
public class Admin {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long adminId;
    @Column(name = "admin_name")
    private String adminName;
    @Column(name = "librarian_list")
    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Librarian> librarianList;
    @Column(name = "library_list")
    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Library> libraryList;

    public Long getAdminId() {
        return adminId;
    }
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
    public String getAdminName() {
        return adminName;
    }
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    public List<Librarian> getLibrarianList() {
        return librarianList;
    }
    public void setLibrarianList(List<Librarian> librarianList) {
        this.librarianList = librarianList;
    }

    public List<Library> getLibraryList() {
        return libraryList;
    }
    public void setLibraryList(List<Library> libraryList) {
        this.libraryList = libraryList;
    }
}