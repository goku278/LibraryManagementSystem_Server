package com.library.Library.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Table(name = "library")
@Entity
//@Data
//@ToString
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "library_id")
    private Long libraryId;
    @Column(name = "library_name")
    private String libraryName;
    @Column(name = "books_list")
    @OneToMany(mappedBy = "library", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Books> booksList;
    @Column(name = "associated_librarians")
    @OneToMany(mappedBy = "library", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Librarian> librarianList;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id")
    @JsonIgnore
    private Admin admin;
    public Long getLibraryId() {
        return libraryId;
    }
    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }
    public String getLibraryName() {
        return libraryName;
    }
    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }
    public List<Books> getBooksList() {
        return booksList;
    }
    public void setBooksList(List<Books> booksList) {
        this.booksList = booksList;
    }
    public List<Librarian> getLibrarianList() {
        return librarianList;
    }
    public void setLibrarianList(List<Librarian> librarianList) {
        this.librarianList = librarianList;
    }
    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }
    public Admin getAdmin() {
        return admin;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}