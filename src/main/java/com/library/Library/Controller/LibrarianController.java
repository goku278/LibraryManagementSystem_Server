package com.library.Library.Controller;
import com.library.Library.Services.LibrarianServices;
import com.library.Library.mapper.Map;
import com.library.Library.models.*;
import com.library.Library.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lms/api/librarian/")
@CrossOrigin(origins = "*")
public class LibrarianController {
    @Autowired
    LibrarianServices librarianServices;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    Map map;
    @PostMapping("register/")
    public Status register(@RequestBody LibrarianModel librarianModel) {
        return librarianServices.register(librarianModel);
    }
    @GetMapping("login/{userName}/{password}/{librarianId}/")
    public Status login(@PathVariable String userName,
                        @PathVariable String password,
                        @PathVariable Long librarianId) {
        return librarianServices.login(userName, password, librarianId);
    }
    // Add books in the library
    @PostMapping("books/add/{libraryId}/")
    public StatusMessage addBooks(@RequestBody BookModel bookModel,
                           @PathVariable Long libraryId) {
        return librarianServices.addBooks(libraryId, bookModel);
    }
    // Get all the issue book request
    @GetMapping("book/issue/request/{librarianId}/")
    public List<BookModel> getIssueRequest(@PathVariable Long librarianId) {
        return librarianServices.checkIssueBookRequest(librarianId);
    }
    @GetMapping("book/issue/cancel/request/{librarianId}/")
    public List<BookModel> getCanceledBooks(@PathVariable Long librarianId) {
        return librarianServices.cancelIssuedBooks(librarianId);
    }
/*    @GetMapping("profile/{librarianId}/")
    public LibrarianModel getLibrarianProfile(@PathVariable Long librarianId) {
        System.out.println("Librarian profile invoked!!");
        return librarianServices.getProfile(librarianId);
    }*/
    @PutMapping("update/Books/")
    public Status updateBook(@RequestBody LibrarianBookModel librarianBookModel) {
        return librarianServices.updateBook(librarianBookModel);
    }
    @PutMapping("marketplace/order/books/{librarianId}/")
    public Status orderBooks(@RequestBody OrderBooksModel order, @PathVariable Long librarianId) {
        return librarianServices.orderBooks(order, librarianId);
    }
    @GetMapping("find/orders/{librarianId}/{orderId}/")
    public List<OrderBooksModel> findOrdersByLibrarianId(@PathVariable Long librarianId, @PathVariable Long orderId) {
       return librarianServices.findOrderById(librarianId, orderId);
    }
    @GetMapping("find/orders/{librarianId}/")
    public List<OrderBooksModel> findOrdersByLibrarianId(@PathVariable Long librarianId) {
        return librarianServices.findOrders(librarianId);
    }
}