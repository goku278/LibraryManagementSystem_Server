package com.library.Library.models;
import com.library.Library.db.AddressUser;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class PersonalDetailsModel {
    private String email;
    private AddressUser addressUser;
    private String role;
    private String qualification;
}