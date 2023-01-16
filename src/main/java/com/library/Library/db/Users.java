package com.library.Library.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "users")
@Entity
//@ToString
@Data
public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "librarian_list")
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Librarian> librarianList;
    @Column(name = "issued_book_items_count")
    private long totalBooksIssued = 0;
    @Column(name = "returned_book_items_count")
    private long totalBooksReturned = 0;
    @Column(name = "request_to_issue_books_count")
    private long totalRequestIssueBooks = 0;
    @Column(name = "user_request_list")
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<UsersRequest> usersRequestList;
    @Column(name = "library_list")
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Library> libraryList;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private PersonalDetails personalDetails;
    @Column(name = "librarian_list")
    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Books> booksList;
    // To transmit data to and fro between Librarian and the User
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "librarian_id")
    @JsonBackReference
    private Librarian librarian;
    @ManyToMany
    @JoinTable(
            name = "librarians",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "librarian_id"))
    Set<Librarian> librarianSet;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Librarian> getLibrarianList() {
        return librarianList;
    }

    public void setLibrarianList(List<Librarian> librarianList) {
        this.librarianList = librarianList;
    }

    public long getTotalBooksIssued() {
        return totalBooksIssued;
    }

    public void setTotalBooksIssued(long totalBooksIssued) {
        this.totalBooksIssued = totalBooksIssued;
    }

    public long getTotalBooksReturned() {
        return totalBooksReturned;
    }

    public void setTotalBooksReturned(long totalBooksReturned) {
        this.totalBooksReturned = totalBooksReturned;
    }

    public long getTotalRequestIssueBooks() {
        return totalRequestIssueBooks;
    }

    public void setTotalRequestIssueBooks(long totalRequestIssueBooks) {
        this.totalRequestIssueBooks = totalRequestIssueBooks;
    }

    public List<Library> getLibraryList() {
        return libraryList;
    }

    public void setLibraryList(List<Library> libraryList) {
        this.libraryList = libraryList;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public List<Books> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Books> booksList) {
        this.booksList = booksList;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public Set<Librarian> getLibrarianSet() {
        return librarianSet;
    }

    public void setLibrarianSet(Set<Librarian> librarianSet) {
        this.librarianSet = librarianSet;
    }
}