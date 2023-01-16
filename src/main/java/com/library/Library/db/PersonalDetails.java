package com.library.Library.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;

@Table(name = "personal_details")
@Entity
//@ToString()
//@Data
public class PersonalDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
//    @Column(name = "person_id")
    private Long personId;
//    @Column(name = "mobile_number")
//    private String mobileNumber;
    @Column(name = "email")
    private String email;
    @OneToOne(mappedBy = "personalDetails", cascade = CascadeType.ALL)
    @JsonManagedReference
    private AddressUser addressUser;
    @Column(name = "user_role")
    private String role;
    @Column(name = "qualification")
    private String qualification;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private Users user;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "librarian_id")
    @JsonBackReference
    private Librarian librarian;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressUser getAddressUser() {
        return addressUser;
    }

    public void setAddressUser(AddressUser addressUser) {
        this.addressUser = addressUser;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }
}