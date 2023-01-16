package com.library.Library.models;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class BookModel {
    private Long bookId;
    private Long userId;
    private Long libraryId;
    private Long librarianId;
    private String bookTitle;
    private String isbn;
    private String bookType;
    private String bookAuthor;
    private long pageNo;
    private String synopsis;
    private double price;
    private String image;
}