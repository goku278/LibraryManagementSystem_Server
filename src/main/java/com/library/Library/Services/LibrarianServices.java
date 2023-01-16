package com.library.Library.Services;

import com.library.Library.config.Constants;
import com.library.Library.db.*;
import com.library.Library.mapper.Map;
import com.library.Library.models.*;
import com.library.Library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibrarianServices {
    @Autowired
    LibrarianRepository librarianRepository;
    @Autowired
    LibraryRepository libraryRepository;
    @Autowired
    BooksRepository booksRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    Map map;
    @Autowired
    UsersRequestRepository usersRequestRepository;

    public Status register(LibrarianModel librarianModel) {
        Librarian l = map.librarianModelToEntity(librarianModel, null);
        librarianRepository.save(l);
        return new Status(200, Constants.LIBRARIAN_REGISTERED_SUCCESSFULLY);
    }

    public Status login(String userName, String password, Long librarianId) {
        Librarian l = librarianRepository.findById(librarianId).orElse(null);
        System.out.println("From line 40 l is : " + l.toString());
//        assert l != null;
        if (l.getLibrarianName().equals(userName) && l.getPersonalDetails().getEmail().equals(password)) {
            return new Status(200, Constants.LOGIN_SUCCESSFUL);
        }
        return new Status(404, Constants.USERNAME_NOT_FOUND);
    }

    public StatusMessage addBooks(Long libraryId, BookModel bookModel) {
        List<Long> librarianIds = booksRepository.findAllLibrarians(libraryId);
        System.out.println("Librarian Ids : " + librarianIds);
        Library l = libraryRepository.findById(libraryId).orElse(null);
        Books b = map.bookModelToEntity(bookModel);
        List<Books> books = new ArrayList<>();
        books.add(b);
        l.setBooksList(books);
        b.setLibrary(l);
        Librarian li = null;
        List<Librarian> librarians = new ArrayList<>();
        for (Long id : librarianIds) {
            li = librarianRepository.findById(id).orElse(null);
            li.setBooksList(books);
            b.setLibrarian(li);
        }
        Iterable<Users> u = usersRepository.findAll();
        for (Users us : u) {
            b.setUsers(us);
        }
        booksRepository.save(b);
        StatusMessage sm = new StatusMessage();
        sm.setCode("200");
        sm.setMessage("Books added to this " + l.getLibraryName() + " successfully");
        return sm;
    }

    public List<BookModel> checkIssueBookRequest(Long librarianId) {
        System.out.println("LibrarianId : " + librarianId);
        List<Long> ids = usersRequestRepository.findBooksIdByLibrarianId(librarianId, "NA");
        System.out.println("ids : " + ids);
        List<BookModel> booksList = new ArrayList<>();
        if (ids != null && ids.size() > 0) {
            for (Long l : ids) {
                Books books = booksRepository.findById(l).orElse(null);
                booksList.add(map.entityToBookModel(books));
            }
        }
        System.out.println("Books : " + booksList);
        return booksList;
    }

    public List<BookModel> cancelIssuedBooks(Long librarianId) {
        System.out.println("LibrarianId : " + librarianId);
        List<Books> booksList = (List<Books>) booksRepository.findAll();
        List<BookModel> books = new ArrayList<>();
        for (Books b : booksList) {
            if (b.getLibrarian().getLibrarianId() == librarianId) {
                Long bookId = b.getBooksId();
                Books books1 = booksRepository.findById(bookId).orElse(null);
                System.out.println("bookId : " + bookId + "\nbooks1 : " + books1);
                if (books1 != null) {
                    books.add(map.entityToBookModel(books1));
                }
            }
        }
        System.out.println("Books : " + books);
        return books;
    }

    /*    public LibrarianModel getProfile(Long userId) {
    //        Librarian l = librarianRepository.findById(librarianId).orElse(null);
            Users u = usersRepository.findById(userId).orElse(null);
            String librarianName = u.getUserName();
    //        System.out.println("Librarian is : " + l.getLibrarianName());
            System.out.println("Librarian Model : " + map.entityToLibrarianModel(l));
            return map.entityToLibrarianModel(l);
        }*/
    public Status updateBook(LibrarianBookModel librarianBookModel) {
        Books b = booksRepository.findById(librarianBookModel.getBookId()).orElse(null);
        b.setPrice(librarianBookModel.getPrice());
        b.setSynopsis(librarianBookModel.getSynopsis());
        b.setIsbn(librarianBookModel.getIsbn());
        b.setSynopsis(librarianBookModel.getSynopsis());
        b.setBookType(librarianBookModel.getBookType());
        b.setBookAuthor(librarianBookModel.getBookAuthor());
        b.setBookTitle(librarianBookModel.getBookTitle());
        b.setPageNo(librarianBookModel.getPageNo());
        List<Books> books = new ArrayList<>();
        books.add(b);
        Library li = libraryRepository.findById(librarianBookModel.getLibraryId()).orElse(null);
        b.setLibrary(li);
        li.setBooksList(books);
        libraryRepository.save(li);
        booksRepository.save(b);
        return new Status(200, "Book with bookId: " + librarianBookModel.getBookId()
                + " updated");
    }

    public Status orderBooks(OrderBooksModel order, Long librarianId) {
        // Outdated code, have to update with the latest changes.
        Librarian librarian = librarianRepository.findById(librarianId).orElse(null);
        Books b = new Books();
        b.setLibrarian(librarian);
        b.setBookAuthor(order.getBookAuthor());
        b.setSynopsis(order.getSynopsis());
        b.setBookType(order.getBookType());
        b.setBookTitle(order.getBookTitle());
        Long li = librarian.getLibrary().getLibraryId();
        Library library = libraryRepository.findById(li).orElse(null);
        Users u = library.getUser();
        b.setLibrary(library);
        b.setUsers(u);
        List<Books> booksList = new ArrayList<>();
        booksList.add(b);
        Order o = new Order();
        o.setBooks(b);
        o.setLibrarian(librarian);
        b.setOrder(o);
        librarian.setBooksList(booksList);
        booksRepository.save(b);
        librarianRepository.save(librarian);
        return new Status(200, "Order books done");
    }

    public List<OrderBooksModel> findOrderById(Long librarianId, Long orderId) {
        List<Long> order = orderRepository.findOrder(librarianId);
        System.out.println("order : " + order);
        List<Order> list = new ArrayList<>();
        for (Long l : order) {
            if (l == orderId) {
                Order o = orderRepository.findById(l).orElse(null);
                list.add(o);
            }
        }
        List<OrderBooksModel> list1 = new ArrayList<>();
        for (Order o : list) {
            list1.add(map.entityToOrderBookModel(o));
        }
        return list1;
    }

    public List<OrderBooksModel> findOrders(Long librarianId) {
        List<Object[]> order = orderRepository.findOrders(librarianId);
        List<OrderBooksModel> orderBooksModelList = new ArrayList<>();
        for (int i = 0; i < order.size(); i++) {
            OrderBooksModel o = new OrderBooksModel();
            System.out.println("o : " + order.get(i)[0] + "\t\t\t\t\t\t" + order.get(i)[1]);
            Long l = orderRepository.findIfMultipleDataExists(order.get(i)[1] + "", order.get(i)[0] + "");
            System.out.println("For " + order.get(i)[1] + " " + order.get(i)[0] + " ===== " + l);
            if (l != null && l > 1) {
                o.setBookAuthor(order.get(i)[0] + "");
                o.setBookTitle(order.get(i)[1] + "");
                o.setLibrarianId(librarianId);
                orderBooksModelList.add(o);
            }
        }
        return orderBooksModelList;
    }
}