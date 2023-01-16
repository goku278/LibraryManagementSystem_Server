package com.library.Library.models;

import lombok.Data;
import lombok.ToString;

//@ToString
//@Data
public class CheckCancelRequest {
    private Long userId;
    private String userName;
    private String totalBooksIssued;
    private String requestToIssueBooks;
    private String approveRequestedBooks;
    private String cancelRequestedBooks;

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

    public String getTotalBooksIssued() {
        return totalBooksIssued;
    }

    public String getRequestToIssueBooks() {
        return requestToIssueBooks;
    }

    public void setRequestToIssueBooks(String requestToIssueBooks) {
        this.requestToIssueBooks = requestToIssueBooks;
    }

    public void setTotalBooksIssued(String totalBooksIssued) {
        this.totalBooksIssued = totalBooksIssued;
    }

    public String getApproveRequestedBooks() {
        return approveRequestedBooks;
    }

    public void setApproveRequestedBooks(String approveRequestedBooks) {
        this.approveRequestedBooks = approveRequestedBooks;
    }

    public String getCancelRequestedBooks() {
        return cancelRequestedBooks;
    }

    public void setCancelRequestedBooks(String cancelRequestedBooks) {
        this.cancelRequestedBooks = cancelRequestedBooks;
    }
}