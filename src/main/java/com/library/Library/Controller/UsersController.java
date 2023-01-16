package com.library.Library.Controller;
import com.library.Library.Services.UsersServices;
import com.library.Library.db.Books;
import com.library.Library.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/lms/api/user/")
@CrossOrigin(origins = "*")
public class UsersController {
    @Autowired
    UsersServices usersServices;
    @PostMapping("register/")
    public Status register(@RequestBody UsersRegisterModel usersRegisterModel) {
        System.out.println("register...");
       return usersServices.register(usersRegisterModel);
    }
    @GetMapping("login/{userName}/{email}/")
    public Status login(@PathVariable String userName,
                        @PathVariable String email) {
        return usersServices.login(userName, email);
    }
    @GetMapping("libraries/")
    public List<LibraryModel> browseAllLibraries() {
        return usersServices.browseAllLibraries();
    }
    @GetMapping("profile/{uId}/{role}/")
    public UserProfileModel getProfile(@PathVariable String uId, @PathVariable String role) {
        System.out.println("User profile invoked!!");
        if (uId.contains("\"")) {
            uId = uId.substring(1, uId.length() - 1);
        }
        return usersServices.getProfile(uId,role);
    }
    @PutMapping("profile/update/{userId}/{role}/")
    public String updateGeneralUser(@RequestBody UserModel userModel,
                                    @PathVariable Long userId, @PathVariable String role) {
        System.out.println("User profile update invoked!!");
        return usersServices.updateUser(userModel, userId, role);
    }
    @PutMapping("dynamicprofile/update/{userId}/{role}/")
    public String updateDynamicUser(@RequestBody UserModel userModel,
                                    @PathVariable Long userId, @PathVariable String role) {
        System.out.println("User profile update invoked!!");
        return usersServices.updateDynamicUser(userModel, userId, role);
    }
    @GetMapping("library/{libraryId}/{userId}/")
    public UserLibraryModel selectLibrary(@PathVariable Long libraryId,
                                          @PathVariable Long userId) {
        return usersServices.getLibrary(libraryId,userId);
    }
    @GetMapping("book/viewAll/{libraryName}/{libraryId}/")
    public List<BookModel> viewAllBooks(@PathVariable String libraryName, @PathVariable String libraryId) {
        return usersServices.viewAllBooks(libraryName, libraryId);
    }
    @GetMapping("books/{category}/")
    public List<BookModel> viewAllBooksByCategory(@PathVariable String category) {
        return usersServices.viewAllBooksByCategory(category);
    }
    @GetMapping("book/viewAll/")
    public List<BookModel> viewAllBooks() {
        return usersServices.viewAllBooks();
    }
    // User issue any books
    @PutMapping("issue/book/{bookId}/{userId}/")
    public Status issueBooks(@PathVariable Long bookId,
                             @PathVariable Long userId) {
        System.out.println("Line 100 called...");
        System.out.println("bookId : " + bookId);
        return usersServices.issueBooks(bookId, userId);
    }
    @PutMapping("cancel/book/{bookId}/{userId}/")
    public Status cancelBooks(@PathVariable Long bookId,
                              @PathVariable Long userId) {
        System.out.println("Line 150 called...");
        return usersServices.cancelBooks(bookId, userId);
    }
    @GetMapping("requests/status/{userId}/")
    public CheckStatusModel checkStatus(@PathVariable Long userId) {
        return usersServices.checkRequestStatus(userId);
    }
    @GetMapping("getBooks/{search}/")
    public List<BookModel> getAllBooksBySearch(@PathVariable String search) {
        return usersServices.findAllBooksBySearchItem(search);
    }
    @PostMapping("request/newBooks/")
    public Status requestNewBooks(@RequestBody RequestBookModel requestBookModel) {
        return usersServices.requestNewBooks(requestBookModel);
    }
    @PutMapping("confirm/order/{bookTitle}/{librarianId}/")
    public Status confirmOrder(@PathVariable String bookTitle, @PathVariable Long librarianId) {
        return usersServices.confirmOrder(bookTitle,librarianId);
    }
}