package com.library.Library.mapper;

import com.library.Library.db.*;
import com.library.Library.models.*;
import com.library.Library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Map {
    @Autowired
    PersonalDetailsRepository personalDetailsRepository;
    @Autowired
    AddressUserRepository addressUserRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    LibraryRepository libraryRepository;
    @Autowired
    UsersRepository usersRepository;

    public LibraryModel libraryModelToEntity(Library l) {
        LibraryModel lm = new LibraryModel();
        lm.setLibraryName(l.getLibraryName());
        lm.setLibraryId(l.getLibraryId() + "");
        List<Librarian> librarianList = l.getLibrarianList();
        List<String> librarians = new ArrayList<>();
        for (Librarian ll : librarianList) {
            librarians.add(ll.getLibrarianName());
        }
        lm.setLibrarianName(librarians);
        return lm;
    }

    public Users modelToUser(UsersRegisterModel usersModel) {
        Users u = new Users();
        PersonalDetails pd = new PersonalDetails();
        pd.setEmail(usersModel.getEmail());
        pd.setQualification(usersModel.getQualification());
        pd.setRole(usersModel.getRole());
        AddressUser au = new AddressUser();
        au.setResident(usersModel.getResident());
        au.setPincode(usersModel.getPincode());
        au.setDistrict(usersModel.getDistrict());
        au.setState(usersModel.getState());
        au.setHouseNo(usersModel.getHouseNo());
        au.setLandMark(usersModel.getLandMark());
        au.setContactNo(usersModel.getContactNo());
        pd.setAddressUser(au);
        pd.setUser(u);
        au.setUser(u);
        au.setPersonalDetails(pd);
        u.setUserName(usersModel.getUserName());
        u.setPersonalDetails(pd);
        return u;
    }

    public UserProfileModel entityToUsersModel(Users u, String role, String userId) {
        UserProfileModel usersModel = new UserProfileModel();
        usersModel.setEmail(u.getPersonalDetails().getEmail());
        usersModel.setRole(u.getPersonalDetails().getRole());
        usersModel.setContactNo(u.getPersonalDetails().getAddressUser().getContactNo());
        usersModel.setUserName(u.getUserName());
        usersModel.setUserId(u.getUserId());
        return usersModel;
    }

    public Librarian librarianModelToEntity(LibrarianModel librarianModel, Users user) {
        Librarian l = new Librarian();
        l.setLibrarianName(librarianModel.getUserName());
        PersonalDetails pd = new PersonalDetails();
        pd.setEmail(librarianModel.getEmail());
        pd.setRole("LIBRARIAN");
        pd.setQualification(librarianModel.getQualification());
        AddressUser ad = new AddressUser();
        ad.setResident(librarianModel.getResident());
        ad.setPincode(librarianModel.getPincode());
        ad.setDistrict(librarianModel.getDistrict());
        ad.setState(librarianModel.getState());
        ad.setHouseNo(librarianModel.getHouseNo());
        ad.setLandMark(librarianModel.getLandMark());
        ad.setContactNo(librarianModel.getContactNo());
        if (user != null) {
            pd.setUser(user);
            ad.setUser(user);
        }
        pd.setAddressUser(ad);
        ad.setPersonalDetails(pd);
        pd.setLibrarian(l);
        l.setPersonalDetails(pd);
        Library li = libraryRepository.findById(librarianModel.getLibraryId()).get();
        l.setLibrary(li);
        Long adminId = Long.valueOf(1);
        Admin add = adminRepository.findById(adminId).orElse(null);
        l.setAdmin(add);
        user.setLibrarian(l);
        usersRepository.save(user);
        return l;
    }

    public AdminLibrarianView entityToAdminLibrarianModel(Librarian librarians) {
        AdminLibrarianView adminLibrarianView = new AdminLibrarianView();
        adminLibrarianView.setUserName(librarians.getLibrarianName());
        adminLibrarianView.setDistrict(librarians.getPersonalDetails().getAddressUser().getDistrict());
        adminLibrarianView.setHouseNo(librarians.getPersonalDetails().getAddressUser().getHouseNo());
        adminLibrarianView.setEmail(librarians.getPersonalDetails().getEmail());
        adminLibrarianView.setRole(librarians.getPersonalDetails().getRole());
        adminLibrarianView.setQualification(librarians.getPersonalDetails().getQualification());
        adminLibrarianView.setContactNo(librarians.getPersonalDetails().getAddressUser().getContactNo());
        adminLibrarianView.setLibraryId(librarians.getLibrary().getLibraryId());
        adminLibrarianView.setLandMark(librarians.getPersonalDetails().getAddressUser().getLandMark());
        adminLibrarianView.setState(librarians.getPersonalDetails().getAddressUser().getState());
        adminLibrarianView.setPincode(librarians.getPersonalDetails().getAddressUser().getPincode());
        adminLibrarianView.setLibraryName(librarians.getLibrary().getLibraryName());
        adminLibrarianView.setResident(librarians.getPersonalDetails().getAddressUser().getResident());
        return adminLibrarianView;
    }

    public Books bookModelToEntity(BookModel bookModel) {
        String isbn[] = new String[7];

        isbn[0] = "ISBN-12345-0012";
        isbn[1] = "ISBN-12347-0012";
        isbn[2] = bookModel.getBookTitle().substring(0, 4) + "" + "-12110012";
        isbn[3] = bookModel.getBookTitle().substring(0, 2) + "" + "-121552112";
        isbn[4] = "ISB-1111-00022252";
        isbn[5] = "ISB-1111-00022252";
        isbn[6] = bookModel.getBookTitle().substring(0,1) + "" + "-1215241";

        Double prices[] = new Double[11];

        prices[0] = 1000D;
        prices[1] = 100D;
        prices[2] = 150D;
        prices[3] = 250D;
        prices[4] = 200D;
        prices[5] = 300D;
        prices[6] = 500D;
        prices[7] = 700D;
        prices[8] = 900D;
        prices[9] = 2000D;
        prices[10] = 2500D;

        Long pageNo[] = new Long[11];

        pageNo[0] = 1000L;
        pageNo[1] = 100L;
        pageNo[2] = 150L;
        pageNo[3] = 250L;
        pageNo[4] = 200L;
        pageNo[5] = 300L;
        pageNo[6] = 50L;
        pageNo[7] = 70L;
        pageNo[8] = 75L;
        pageNo[9] = 40L;
        pageNo[10] = 90L;

        Books b = new Books();
        b.setBookTitle(bookModel.getBookTitle());
        if (bookModel.getIsbn() == null || bookModel.getIsbn().isEmpty()) {
            int max = 4, min = 0, range = max - min + 1, rand = 0;
            rand = (int)(Math.random() * range) + min;
            b.setIsbn(isbn[rand]);
        } else {
            b.setIsbn(bookModel.getIsbn());
        }
        b.setBookType(bookModel.getBookType());
        b.setBookAuthor(bookModel.getBookAuthor());
        if (bookModel.getPageNo() == 0) {
            int max = 9, min = 0, range = max - min +1, rand = 0;
            rand = (int)(Math.random() * range) + min;
            b.setPageNo(pageNo[rand]);
        }
        else {
            b.setPageNo(bookModel.getPageNo());
        }
        if (bookModel.getSynopsis() == null || bookModel.getSynopsis().isEmpty()) {
            if (bookModel.getBookTitle().contains("Comics") || bookModel.getBookTitle().contains("Manga")) {
                b.setSynopsis("Action, Adventure");
            }
            if (bookModel.getBookTitle().contains("Megazines")) {
                b.setSynopsis("Recent trends, Hot stories");
            }
            if (bookModel.getBookTitle().contains("Computer Science")) {
                b.setSynopsis("Computer Books");
            }
            if (bookModel.getBookTitle().contains("Science")) {
                b.setSynopsis("Science Books");
            }
            else {
                b.setSynopsis("Books");
            }
        }
        else {
            b.setSynopsis(bookModel.getSynopsis());
        }
        if (bookModel.getPrice() == 0) {
            int max = 9, min = 1, range = max - min +1, rand = 0;
            rand = (int)(Math.random() * range) + min;
            bookModel.setPrice(prices[rand]);
        }
        else {
            b.setPrice(bookModel.getPrice());
        }
        b.setImage(bookModel.getImage());
        return b;
    }

    public CheckIssueRequestModel entityToCheckIssueRequestModel(Users u) {
        CheckIssueRequestModel c1 = new CheckIssueRequestModel();
        c1.setUserId(u.getUserId());
        c1.setUserName(u.getUserName());
        c1.setTotalBooksIssued(u.getTotalBooksIssued());
        return c1;
    }

    public CheckCancelRequest entityToCancelRequestModel(Users u) {
        CheckCancelRequest ch = new CheckCancelRequest();
        ch.setTotalBooksIssued(u.getTotalBooksIssued() + "");
        ch.setUserId(u.getUserId());
        ch.setUserName(u.getUserName());
        return ch;
    }

    public LibrarianModel entityToLibrarianModel(Librarian l) {
        LibrarianModel lm = new LibrarianModel();
        lm.setLibrarianId(l.getLibrarianId());
        lm.setContactNo(l.getPersonalDetails().getAddressUser().getContactNo());
        lm.setDistrict(l.getPersonalDetails().getAddressUser().getDistrict());
        lm.setUserName(l.getLibrarianName());
        lm.setEmail(l.getPersonalDetails().getEmail());
        lm.setRole(l.getPersonalDetails().getRole());
        lm.setQualification(l.getPersonalDetails().getQualification());
        lm.setPincode(l.getPersonalDetails().getAddressUser().getPincode());
        lm.setResident(l.getPersonalDetails().getAddressUser().getResident());
        lm.setLibraryId(l.getLibrary().getLibraryId());
        lm.setHouseNo(l.getPersonalDetails().getAddressUser().getHouseNo());
        lm.setLandMark(l.getPersonalDetails().getAddressUser().getLandMark());
        lm.setState(l.getPersonalDetails().getAddressUser().getState());
        lm.setPincode(l.getPersonalDetails().getAddressUser().getPincode());
        return lm;
    }

    public BookModel entityToBookModel(Books b) {
        BookModel bm = new BookModel();
        bm.setBookId(b.getBooksId());
        bm.setUserId(b.getUsers().getUserId());
        bm.setLibraryId(b.getLibrary().getLibraryId());
        bm.setLibrarianId(b.getLibrarian().getLibrarianId());
        bm.setBookAuthor(b.getBookAuthor());
        bm.setPrice(b.getPrice());
        bm.setIsbn(b.getIsbn());
        bm.setBookType(b.getBookType());
        bm.setSynopsis(b.getSynopsis());
        bm.setBookTitle(b.getBookTitle());
        bm.setPageNo(b.getPageNo());
        bm.setImage(b.getImage());
        return bm;
    }

    public LibraryModel entityToLibraryModel(Library l) {
        LibraryModel lm = new LibraryModel();
        List<Librarian> librarianList = l.getLibrarianList();
        List<String> librarians = new ArrayList<>();
        for (Librarian ll : librarianList) {
            librarians.add(ll.getLibrarianName());
        }
        lm.setLibraryId(l.getLibraryId() + "");
        lm.setLibrarianName(librarians);
        lm.setLibraryName(l.getLibraryName());
        return lm;
    }

    public OrderBooksModel entityToOrderBookModel(Order o) {
        OrderBooksModel orderBooksModel = new OrderBooksModel();
        orderBooksModel.setBooksId(o.getBooks().getBooksId());
        orderBooksModel.setBookAuthor(o.getBooks().getBookAuthor());
        orderBooksModel.setBooksId(o.getBooks().getBooksId());
        orderBooksModel.setBookTitle(o.getBooks().getBookTitle());
        orderBooksModel.setIsbn(o.getBooks().getIsbn());
        orderBooksModel.setBookType(o.getBooks().getBookType());
        orderBooksModel.setBookAuthor(o.getBooks().getBookAuthor());
        orderBooksModel.setPageNo(o.getBooks().getPageNo());
        orderBooksModel.setSynopsis(o.getBooks().getSynopsis());
        orderBooksModel.setPrice(o.getPrice());
        return orderBooksModel;
    }

    public AdminProfile adminEntityToModel(Admin a, Long adminId, AdminRepository adminRepository) {
        AdminProfile ap = new AdminProfile();
        ap.setUserName(a.getAdminName());
        AddressUser au = addressUserRepository.getAdminAddress(adminId);
        if (au != null) {
            ap.setDistrict(au.getDistrict());
            ap.setLandMark(au.getLandMark());
            ap.setPincode(au.getPincode());
            ap.setState(au.getState());
            ap.setContactNo(au.getContactNo());
            ap.setResident(au.getResident());
            ap.setHouseNo(au.getLandMark());
        }
        PersonalDetails pd = personalDetailsRepository.getPersonalDetails(adminId);
        if (pd != null) {
            ap.setEmail(pd.getEmail());
            ap.setQualification(pd.getQualification());
            ap.setRole(pd.getRole());
        }
        return ap;
    }

    public OrderBooksModel entityToOrderBookModel2(Order o) {
        OrderBooksModel orderBooksModel = new OrderBooksModel();
        if (o.getBooks().getBookAuthor() != null) {
            orderBooksModel.setBookAuthor(o.getBooks().getBookAuthor());
        }
        if (o.getBooks().getBookTitle() != null) {
            orderBooksModel.setBookTitle(o.getBooks().getBookTitle());
        }
        return orderBooksModel;
    }
}