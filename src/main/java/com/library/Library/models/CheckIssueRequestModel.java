package com.library.Library.models;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CheckIssueRequestModel {
    private Long userId;
    private String userName;
    private Long totalBooksIssued;
    private String approveRequestedBooks;
    private String cancelledRequestedBooks;
    private String requestToIssueBooks;
    private String requestIssueBookId;
}