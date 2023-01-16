package com.library.Library.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "librarian")
@Entity
//@Data
//@ToString
public class Librarian {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
//    @Column(name = "librarian_id")
    private Long librarianId;
    @Column(name = "librarian_name")
    private String librarianName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id")
    @JsonIgnore
    private Library library;
    @OneToMany(mappedBy = "librarian", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Books> booksList;
    @OneToOne(mappedBy = "librarian", cascade = CascadeType.ALL)
    @JsonManagedReference
    private PersonalDetails personalDetails;
    @OneToOne(mappedBy = "librarian", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    @JsonIgnore
    private Admin admin;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "librarian", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Order> ordersList;
    @ManyToMany(mappedBy = "librarianSet")
    Set<Users> user;
    public Long getLibrarianId() {
        return librarianId;
    }
    public void setLibrarianId(Long librarianId) {
        this.librarianId = librarianId;
    }
    public String getLibrarianName() {
        return librarianName;
    }
    public void setLibrarianName(String librarianName) {
        this.librarianName = librarianName;
    }
    public Library getLibrary() {
        return library;
    }
    public void setLibrary(Library library) {
        this.library = library;
    }
    public List<Books> getBooksList() {
        return booksList;
    }
    public void setBooksList(List<Books> booksList) {
        this.booksList = booksList;
    }
    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }
    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }
    public Users getUsers() {
        return users;
    }
    public void setUsers(Users users) {
        this.users = users;
    }
    public Admin getAdmin() {
        return admin;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public List<Order> getOrdersList() {
        return ordersList;
    }
    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }
    public Set<Users> getUser() {
        return user;
    }
    public void setUser(Set<Users> user) {
        this.user = user;
    }
    @Override
    public String toString() {
        return "Librarian{" +
                "librarianId=" + librarianId +
                ", librarianName='" + librarianName + '\'' +
                ", library=" + library +
                ", booksList=" + booksList +
                ", personalDetails=" + personalDetails +
                ", users=" + users +
                ", admin=" + admin +
                ", ordersList=" + ordersList +
                ", user=" + user +
                '}';
    }
}
