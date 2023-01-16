package com.library.Library.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.library.Library.db.Admin;
import com.library.Library.db.Librarian;
import com.library.Library.db.Users;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class LibraryModel {
    private String libraryId;
    private String libraryName;
    private List<String> librarianName;
//    private List<Librarian> librarianList;
}
