package com.library.Library.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;

@ToString
@Data
public class CheckStatusModel {
    private Long userId;
    private long totalBooksIssued = 0;
    private long totalBooksReturned = 0;
    private String approveRequestedBooks;  // values in either "yes" or "no"
    private String cancelRequestedBooks;  // values in either "yes" or "no"
    private String requestToIssueBooks;  // values in either "yes" or "no"
    private String requestIssueBookId; // If
}
