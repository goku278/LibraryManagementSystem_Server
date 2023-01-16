package com.library.Library.Services;
import com.library.Library.config.Constants;
import com.library.Library.db.*;
import com.library.Library.mapper.Map;
import com.library.Library.models.*;
import com.library.Library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class UsersServices {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    Map map;
    @Autowired
    LibraryRepository libraryRepository;
    @Autowired
    LibrarianRepository librarianRepository;
    @Autowired
    BooksRepository booksRepository;
    @Autowired
    PersonalDetailsRepository personalDetailsRepository;
    @Autowired
    AddressUserRepository addressUserRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UsersRequestRepository usersRequestRepository;
    @Autowired
    OrderRepository orderRepository;
    public Status register(UsersRegisterModel usersModel) {
        Users u = map.modelToUser(usersModel);
        String email = usersModel.getEmail();
        System.out.println("email : " + email);
        if (usersModel.getRole().equalsIgnoreCase("ADMIN")) {
            Admin a = new Admin();
            a.setAdminName(usersModel.getUserName());
            adminRepository.save(a);
        }
        if (email != null) {
            List<Long> list = personalDetailsRepository.getId(email);
            if (list != null && list.size() > 0) {
                System.out.println("list : " + list);
                return new Status(200, Constants.USER_ALREADY_EXISTS);
            }
        }
        usersRepository.save(u);
        return new Status(200, Constants.REGISTRATION_SUCCESSFUL);
    }
    public List<LibraryModel> browseAllLibraries() {
        List<Library> libraryList = (List<Library>) libraryRepository.findAll();
        List<LibraryModel> libraryModels = new ArrayList<>();
        for (Library l : libraryList) {
            libraryModels.add(map.libraryModelToEntity(l));
        }
        return libraryModels;
    }
    public UserProfileModel getProfile(String userId, String role) {
//        Long uId = Long.valueOf(userId);
        if (!userId.contains("@")) {
            Users u = new Users();
            if (role.equalsIgnoreCase("LIBRARIAN")) {
                Long uId = usersRepository.findUserIdByLibrarianId(userId);
                u = usersRepository.findById(uId).orElse(null);
            }
            else {
                u = usersRepository.findById(Long.valueOf(userId)).orElse(null);
            }
            return map.entityToUsersModel(u,role,userId);
        }
        if (personalDetailsRepository.getId(userId) != null && personalDetailsRepository.getId(userId).size() > 0) {
//            if (uId != null) {
            System.out.println("From line 60 personalDetailsRepository.getId(userId) : " + personalDetailsRepository.getId(userId));
            long uId = personalDetailsRepository.getId(userId).get(0);
            Users u = usersRepository.findById(uId).orElse(null);
            return map.entityToUsersModel(u,role,userId);
//            }
        }
        return new UserProfileModel("User does not exists");
    }
    public Status login(String userName, String password) {
        System.out.println("From line 65 password is : " + password);
        List<Long> userId = personalDetailsRepository.getId(password.trim());
        System.out.println("userId : " + userId);
        Status status = new Status();
        if (userId == null || userId.size() == 0) {
            status.setCode(404);
            status.setMessage(Constants.USERNAME_NOT_FOUND);
            return status;
        }
        Users u = usersRepository.findById(userId.get(0)).orElse(null);
        if (u == null) {
            return new Status(404, "USER NOT FOUND");
        }
        String role = personalDetailsRepository.findUserRoleByUserId(userId.get(0));
        System.out.println("Role is : " + role);
        System.out.println("user details are : " + u.getPersonalDetails().getRole() + "\t" + u.getPersonalDetails().getEmail());
        System.out.println("userName == u.getUserName() " + userName + "==" + u.getUserName());
        System.out.println("u.getPersonalDetails().getEmail() == password " + u.getPersonalDetails().getEmail() + "==" + password);
        if (u.getPersonalDetails().getEmail().equals(password) &&
                u.getUserName().equals(userName)) {
            status.setCode(200);
            if (role.equalsIgnoreCase("LIBRARIAN")) {
                status.setUserId(u.getLibrarian().getLibrarianId());
            }
            else {
                status.setUserId(u.getUserId());
            }
            status.setMessage(Constants.LOGIN_SUCCESSFUL);
            if (role == null) {
                role = "Role Not Found";
            }
            status.setUserRole(role);
        } else {
            status.setCode(404);
            status.setMessage(Constants.USERNAME_NOT_FOUND);
        }
        return status;
    }
    public UserLibraryModel getLibrary(Long libraryId, Long userId) {
        Users u = usersRepository.findById(userId).orElse(null);
        Library library = libraryRepository.findById(libraryId).orElse(null);
        List<Library> l1 = new ArrayList<>();
        l1.add(library);
        u.setLibraryList(l1);
        usersRepository.save(u);
        UserLibraryModel userLibraryModel = new UserLibraryModel();
        userLibraryModel.setUserId(userId);
        userLibraryModel.setUserName(u.getUserName());
        userLibraryModel.setUserEmail(u.getPersonalDetails().getEmail());
        userLibraryModel.setLibraryId(libraryId);
        userLibraryModel.setLibraryName(library.getLibraryName());
        userLibraryModel.setLibrarianList(library.getLibrarianList());
        return userLibraryModel;
    }
    public List<BookModel> viewAllBooks(String libraryName, String libraryId) {
        List<BookModel> bookModelList = new ArrayList<>();
        List<Object[]> bm = booksRepository.findBooksByLibraryId(Long.valueOf(libraryId));
        if (bm != null && bm.size() > 0) {
            for (int i = 0; i < bm.size(); i++) {
                BookModel bm1 = new BookModel();
                bm1.setBookId(Long.valueOf(String.valueOf(bm.get(i)[0])));
                bm1.setLibraryId(Long.valueOf(String.valueOf(bm.get(i)[1])));
                bm1.setLibrarianId(Long.valueOf(String.valueOf(bm.get(i)[2])));
                bm1.setBookAuthor(bm.get(i)[3] + "");
                bm1.setBookTitle(bm.get(i)[4] + "");
                bm1.setIsbn(bm.get(i)[5] + "");
                bm1.setBookType(bm.get(i)[6] + "");
                bm1.setPageNo(Long.parseLong(String.valueOf(bm.get(i)[7])));
                bm1.setSynopsis(bm.get(i)[8] + "");
                bm1.setPrice((Double) bm.get(i)[9]);
                bm1.setImage(bm.get(i)[10] + "");
                bookModelList.add(bm1);
            }
        }
        return bookModelList;
    }
    public Status issueBooks(Long bookId, Long userId) {
        System.out.println("bookId : " + bookId);
        System.out.println("userId : " + userId);
        Users u = usersRepository.findById(userId).orElse(null);
        Long librarianId = booksRepository.findByBookId(bookId);
        UsersRequest ur = new UsersRequest();
        if (u != null) {
            String bookAndLibrarianAndUserId = "\t" + "bookId\t" + bookId + "\n" + "\t" + "librarianId\t" + librarianId + "\n" + "\t"
                    + "userId\t" + userId;
            System.out.println("bookAndLibrarianAndUserId : " + bookAndLibrarianAndUserId);
            ur.setUser(u);
            ur.setRequestToIssueBooks("REQUEST INITIATED");
            ur.setCancelRequestedBooks("NA");
            ur.setApproveRequestedBooks("ISSUE BOOK REQUEST RAISED BY USER");
            ur.setBookId(bookId+"");
            ur.setLibrarianId(librarianId+"");
            ur.setUserId(userId+"");
            usersRequestRepository.save(ur);
        }
        usersRepository.save(u);
        System.out.println("LibrarianId : " + librarianId);
        return new Status(200, Constants.ISSUE_REQUEST);
    }
    public Status cancelBooks(Long bookId, Long userId) {
        System.out.println("bookId : " + bookId);
        System.out.println("userId : " + userId);
        Users u = usersRepository.findById(userId).orElse(null);
        Long librarianId = booksRepository.findByBookId(bookId);
        UsersRequest ur = new UsersRequest();
        if (u != null) {
            ur.setUser(u);
            ur.setRequestToIssueBooks("REQUEST CANCELLED");
            ur.setCancelRequestedBooks("YES");
            ur.setApproveRequestedBooks("ISSUE BOOK REQUEST CANCELLED BY USER");
            ur.setBookId(bookId+"");
            ur.setLibrarianId(librarianId+"");
            ur.setUserId(userId+"");
            usersRequestRepository.save(ur);
        }
        usersRepository.save(u);
        System.out.println("LibrarianId : " + librarianId);
        return new Status(200, Constants.CANCEL_REQUEST_ACK);
    }
    public CheckStatusModel checkRequestStatus(Long userId) {
        Users u = usersRepository.findById(userId).orElse(null);
        CheckStatusModel ch = new CheckStatusModel();
        ch.setUserId(userId);
        ch.setTotalBooksIssued(u.getTotalBooksIssued());
        return ch;
    }
    public String updateUser(UserModel userModel, Long userId, String role) {
        System.out.println("userId : " + userId);
        System.out.println("role is : " + role);
        Users u = new Users();
        if (role.equalsIgnoreCase("LIBRARIAN")) {
            Long uId = usersRepository.findUserIdByLibrarianId(userId+"");
            System.out.println("uId : " + uId);
            u = usersRepository.findById(uId).orElse(null);
            personalDetailsRepository.updateEmail(userModel.getEmail(), uId);
            addressUserRepository.updateContactNo(userModel.getContactNo(), uId);
            u.setUserName(userModel.getUserName());
        }
        else {
            System.out.println("userId : " + userId);
            u = usersRepository.findById(Long.valueOf(userId)).orElse(null);
            personalDetailsRepository.updateEmail(userModel.getEmail(), userId);
            addressUserRepository.updateContactNo(userModel.getContactNo(), userId);
            u.setUserName(userModel.getUserName());
        }
//        Users u = usersRepository.findById(userId).orElse(null);

        usersRepository.save(u);
        return "General user updated successfully!";
    }
    public String updateDynamicUser(UserModel userModel, Long userId, String role) {
        System.out.println("userId : " + userId);
        System.out.println("role is : " + role);
        Users u = new Users();
        u = usersRepository.findById(userId).orElse(null);
        personalDetailsRepository.updateEmail(userModel.getEmail(), userId);
        addressUserRepository.updateContactNo(userModel.getContactNo(), userId);
        u.setUserName(userModel.getUserName());
        usersRepository.save(u);
        return "General user updated successfully!";
    }
    public List<BookModel> viewAllBooks() {
        List<Books> books = (List<Books>) booksRepository.findAll();
        List<BookModel> bookModelList = new ArrayList<>();
        for (Books b : books) {
            BookModel bm = map.entityToBookModel(b);
            bookModelList.add(bm);
        }
        return bookModelList;
    }
    public List<BookModel> viewAllBooksByCategory(String category) {
        List<Books> booksList = (List<Books>) booksRepository.findAll();
        List<BookModel> bookModelList = new ArrayList<>();
        for (Books b : booksList) {
            if (b.getBookType() != null) {
                if (b.getBookType().contains(category)) {
                    bookModelList.add(map.entityToBookModel(b));
                }
            }
        }
        System.out.println("Category is : " + category);
        System.out.println("Book List : " + bookModelList);
        return bookModelList;
    }
    public List<BookModel> findAllBooksBySearchItem(String text) {
        String split[]= text.split("\\s+");
        if (split.length > 1) {
            String s = split[0].substring(0, 1).toUpperCase() + split[0].substring(1);
            String s1 = split[1].substring(0,1).toUpperCase() + split[1].substring(1);
            text = "";
            text = s + " " + s1;
        }
        if (split.length == 1) {
            String s = split[0].substring(0, 1).toUpperCase() + split[0].substring(1);
            text = "";
            text = s;
        }
        System.out.println("parsed text is : " + text);
        Books book = booksRepository.findBookByBookTitle(text.trim());
        BookModel bm = new BookModel();
        if (book != null) {
            System.out.println("book : " + book.getBookTitle() + "\t" + book.getBooksId());
            bm = map.entityToBookModel(book);
        }
        List<Books> books = (List<Books>) booksRepository.findAll();
        List<BookModel> bookModelList = new ArrayList<>();
        for (Books b: books) {
            if (b.getBookType() != null) {
                if (b.getBookType().contains(text.trim())) {
                    bookModelList.add(map.entityToBookModel(b));
                }
            }
            if (b.getBookTitle() != null) {
                if (b.getBookTitle().contains(text.trim())) {
                    bookModelList.add(map.entityToBookModel(b));
                }
            }
        }
        if (bm!=null) {
            bookModelList.add(bm);
        }
        return bookModelList;
    }
    public Status requestNewBooks(RequestBookModel requestBookModel) {
        List<Librarian> librarians = (List<Librarian>) librarianRepository.findAll();
        List<Long> lId = new ArrayList<>();
        for (Librarian li: librarians) {
            if (li != null && li.getLibrarianId() != null) {
                Order o = new Order();
                o.setBookTitle(requestBookModel.getTitle());
                o.setBookAuthor(requestBookModel.getAuthor());
                o.setLibrarian(li);
                orderRepository.save(o);
            }
        }
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//        pq.add(1);
        Status status = new Status(200, "Order Placed Successfully");
        System.out.println("status : " + status);
        return status;
    }
    public Status confirmOrder(String bookTitle, Long librarianId) {
        bookTitle = bookTitle.trim();
        System.out.println("bookTitle : " + bookTitle);
        List<Long> orderRequestIds = orderRepository.findAllOrderIdsByBookTitle(bookTitle,librarianId);
        for (Long l: orderRequestIds) {
            System.out.println("l --> " + l);
            orderRepository.deleteById(l);
        }
        List<Long> orderIds = orderRepository.findAllOrderIdsByLibrarianIdAndBookTitle(librarianId,bookTitle);
        System.out.println("orderIds : " + orderIds);
        if (orderIds!=null && orderIds.size() > 0) {
            for (int i = 0; i < orderIds.size(); i++) {
                if (i!=0) {
                    orderRepository.deleteById(orderIds.get(i));
                }
            }
        }
        return new Status(200,"Order is confirmed");
    }
}