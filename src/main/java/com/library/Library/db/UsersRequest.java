package com.library.Library.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Table(name = "users_request")
@Entity
//@ToString
@Data
public class UsersRequest {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userRequestId;
    @Column(name = "approve_request_to_issue_books")
    private String approveRequestedBooks;  // values in either "yes" or "no"
    @Column(name = "cancel_request_to_issue_books")
    private String cancelRequestedBooks;  // values in either "yes" or "no"
    @Column(name = "request_to_issue_books")
    private String requestToIssueBooks;  // values in either "yes" or "no"
    @Column(name = "cancel_issue_book_id")
    private String cancelIssueBookId;  // If users opts for any cancellation to any request made for issuing books
    @Column(name = "request_issue_book_id")
    private String requestIssueBookId; //
    @Column(name = "bookk_id")
    private String bookId;
    @Column(name = "userr_id")
    private String userId;
    @Column(name = "librariann_id")
    private String librarianId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users user;
}