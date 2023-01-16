package com.library.Library.models;

import com.library.Library.db.Librarian;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserLibraryModel {
    private Long userId;
    private String userName;
    private String userEmail;
    private Long libraryId;
    private String libraryName;
    private List<Librarian> librarianList;
}