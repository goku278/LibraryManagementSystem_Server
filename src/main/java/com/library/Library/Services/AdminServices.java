package com.library.Library.Services;
import com.library.Library.config.Constants;
import com.library.Library.db.*;
import com.library.Library.mapper.Map;
import com.library.Library.models.*;
import com.library.Library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminServices {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    LibraryRepository libraryRepository;
    @Autowired
    LibrarianRepository librarianRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    AddressUserRepository addressUserRepository;
    @Autowired
    PersonalDetailsRepository personalDetailsRepository;
    @Autowired
    Map map;
    public Status linkLibrarianToLibrary(Long libraryId, LibrarianModel librarianModel) {
        Users u = new Users();
        System.out.println("Librarian Name : " + librarianModel.getUserName());
        u.setUserName(librarianModel.getUserName());

        Library li = libraryRepository.findById(libraryId).orElse(null);
        Librarian l = map.librarianModelToEntity(librarianModel, u);
        List<Librarian> librarianSet = new ArrayList<>();
        librarianSet.add(l);
        li.setLibrarianList(librarianSet);
        libraryRepository.save(li);


        return new Status(200, Constants.LINK_LIBRARIAN_TO_LIBRARY);
       /* Library library = libraryRepository.findById(libraryId).orElse(null);
        Librarian l = new Librarian();
        Users u = new Users();
        u.setUserName(librarianModel.getUserName());
        l.setLibrary(library);
        Long adminId = Long.valueOf(1);
        Admin ad = adminRepository.findById(adminId).orElse(null);
        l.setAdmin(ad);
        l.setLibrarianName(librarianModel.getUserName());
        PersonalDetails pd = new PersonalDetails();
        pd.setEmail(librarianModel.getEmail());
        pd.setRole(librarianModel.getRole());
        pd.setQualification(librarianModel.getQualification());
        AddressUser add = new AddressUser();
        add.setPincode(librarianModel.getPincode());
        add.setHouseNo(librarianModel.getHouseNo());
        add.setDistrict(librarianModel.getDistrict());
        add.setResident(librarianModel.getResident());
        add.setState(librarianModel.getState());
        add.setHouseNo(librarianModel.getHouseNo());
        add.setContactNo(librarianModel.getContactNo());
        add.setPersonalDetails(pd);
        pd.setAddressUser(add);
        pd.setLibrarian(l);
        l.setPersonalDetails(pd);
        u.setPersonalDetails(pd);
        add.setLandMark(librarianModel.getLandMark());
        List<Librarian> librarianList = new ArrayList<>();
        librarianList.add(l);
        ad.setLibrarianList(librarianList);
        librarianRepository.save(l);
        adminRepository.save(ad);
        usersRepository.save(u);
        return new Status(200, Constants.LINK_LIBRARIAN_TO_LIBRARY);*/
    }
    public List<LibraryModel> viewAllLibraries() {
        Iterable<Library> librarianModels = libraryRepository.findAll();
        List<LibraryModel> lm = new ArrayList<>();
        for (Library l : librarianModels) {
            lm.add(map.entityToLibraryModel(l));
        }
        return lm;
    }
    public List<AdminLibrarianView> viewAllLibrarians() {
        Iterable<Librarian> librarianList = librarianRepository.findAll();
        List<AdminLibrarianView> librarianViewList = new ArrayList<>();
        for (Librarian l : librarianList) {
            try {
                AdminLibrarianView a = new AdminLibrarianView();
                a.setUserName(l.getLibrarianName());
                a.setLibrarianId(l.getLibrarianId()+"");
                a.setLibraryId(l.getLibrary().getLibraryId());
                a.setRole(l.getPersonalDetails().getRole());
                a.setState(l.getPersonalDetails().getAddressUser().getState());
                a.setPincode(l.getPersonalDetails().getAddressUser().getPincode());
                a.setQualification(l.getPersonalDetails().getQualification());
                a.setResident(l.getPersonalDetails().getAddressUser().getResident());
                a.setEmail(l.getPersonalDetails().getEmail());
                a.setContactNo(l.getPersonalDetails().getAddressUser().getContactNo());
                a.setLandMark(l.getPersonalDetails().getAddressUser().getLandMark());
                a.setLibraryName(l.getLibrary().getLibraryName());
                a.setHouseNo(l.getPersonalDetails().getAddressUser().getHouseNo());
                a.setDistrict(l.getPersonalDetails().getAddressUser().getDistrict());
                librarianViewList.add(a);
            } catch (Exception e) {}
        }
        System.out.println("Librarian List");
        System.out.println(librarianViewList);
        return librarianViewList;
    }
    public Status register(UsersRegisterModel usersRegisterModel) {
        System.out.println("usersRegisterModel : " + usersRegisterModel.toString());
        Users u = map.modelToUser(usersRegisterModel);
        Users u1= usersRepository.save(u);
        System.out.println("u1 is : " + u1);
        System.out.println("u1 user_id is: " + u1.getUserId());
        AddressUser ad = u1.getPersonalDetails().getAddressUser();
        PersonalDetails pd = u1.getPersonalDetails();
        pd.setUser(u1);
        ad.setPersonalDetails(pd);
        ad.setUser(u1);
        addressUserRepository.save(ad);
        personalDetailsRepository.save(pd);
        Admin a = new Admin();
        a.setAdminId(u.getUserId());
        a.setAdminName(u.getUserName());
        adminRepository.save(a);
        return new Status(200, Constants.REGISTRATION_SUCCESSFUL);
    }
    public Status login(UsersRegisterModel usersRegisterModel, Long userId) {
        Users u = usersRepository.findById(userId).orElse(null);
        System.out.println("u is : " + u.toString());
        if (u.getUserName().equals(usersRegisterModel.getUserName()) &&
                u.getPersonalDetails().getEmail().equals(usersRegisterModel.getEmail())) {
            return new Status(200, Constants.ADMIN_LOGIN_SUCCESSFUL);
        }
        return new Status(404, Constants.USERNAME_NOT_FOUND);
    }
    public Status deleteLibrary(Long libraryId) {
        libraryRepository.deleteById(libraryId);
        return new Status(200, Constants.LIBRARY_DELETED_SUCCESSFULLY);
    }
    public Status deleteLibrarian(Long librarianId) {
        librarianRepository.deleteById(librarianId);
        return new Status(200, Constants.LIBRARIAN_DELETED_SUCCESSFULLY);
    }
    public Status deleteAllLibrary() {
        libraryRepository.deleteAll();
        return new Status(200, Constants.LIBRARIES_DELETED_SUCCESSFULLY);
    }
    public Status deleteAllLibrarian() {
        librarianRepository.deleteAll();
        return new Status(200, Constants.LIBRARIANS_DELETED_SUCCESSFULLY);
    }
    public Status addLibrary(LibraryAdminModel lm) {
        Library l = new Library();
        l.setLibraryName(lm.getLibraryName());
        Admin a = adminRepository.findById(lm.getAdminId()).orElse(null);
        l.setAdmin(a);
        List<Library> libraryList = new ArrayList<>();
        libraryList.add(l);
        a.setLibraryList(libraryList);
        Users u = usersRepository.findById(lm.getUserId()).orElse(null);
        l.setUser(u);
        libraryRepository.save(l);
        return new Status(200, Constants.LIBRARY_ADDED_SUCCESSFULLY);
    }
    public Status addLibraryy(LibraryAdminModel lm) {
        lm.setUserId(1L);
        Admin ad = adminRepository.findById(lm.getAdminId()).orElse(null);
        Library l = new Library();
        l.setLibraryName(lm.getLibraryName());
        l.setAdmin(ad);
        Users u = usersRepository.findById(lm.getUserId()).orElse(null);
        l.setUser(u);
        List<Library> libraryList = new ArrayList<>();
        libraryList.add(l);
        System.out.println("libraryList : " + libraryList);
        System.out.println("lm.libraryName : " + lm.getLibraryName());
        System.out.println("lm.adminId : " + lm.getAdminId());
        System.out.println("lm.userId : " + lm.getUserId());
//        libraryRepository.save(l);
        ad.setLibraryList(libraryList);
        adminRepository.save(ad);
        return new Status(200, Constants.LIBRARY_ADDED_SUCCESSFULLY);
    }
    public Status addLibrarian(LibrarianModel librarianModel) {
        Librarian l = new Librarian();
        l.setLibrarianName(librarianModel.getUserName());
        l.setLibrarianId(l.getLibrarianId());
        PersonalDetails pd = new PersonalDetails();
        pd.setEmail(librarianModel.getEmail());
        pd.setRole(librarianModel.getRole());
        pd.setQualification(librarianModel.getQualification());
        AddressUser ad = new AddressUser();
        ad.setContactNo(librarianModel.getContactNo());
        ad.setHouseNo(librarianModel.getHouseNo());
        ad.setLandMark(librarianModel.getLandMark());
        ad.setState(librarianModel.getState());
        ad.setDistrict(librarianModel.getDistrict());
        ad.setPincode(librarianModel.getPincode());
        pd.setAddressUser(ad);
        l.setPersonalDetails(pd);
        Admin a = adminRepository.findById(librarianModel.getAdminId()).orElse(null);
        l.setAdmin(a);
        librarianRepository.save(l);
        return new Status(200, Constants.LIBRARIAN_ADDED_SUCCESSFULLY);
    }
    public AdminProfile getAdminProfile(Long adminId) {
        Admin a = adminRepository.findById(adminId).orElse(null);
//        System.out.println("Admin is : " + a);
        return map.adminEntityToModel(a, adminId, adminRepository);
    }
    public String updateAdminProfile(UpdateAdminModel updateAdminModel, Long adminId) {
        Admin a = adminRepository.findById(adminId).orElse(null);
        a.setAdminName(updateAdminModel.getAdminName());
        System.out.println("Admin name is :" + updateAdminModel.getAdminName());
        personalDetailsRepository.updateEmail(updateAdminModel.getEmail(), adminId);
        addressUserRepository.updateContactNo(updateAdminModel.getContactNo(), adminId);
        adminRepository.save(a);
        return "Admin profile successfully updated!";
    }
    public Set<UserModel> getAllUsers() {
        Set<UserModel> userModels = new LinkedHashSet<>();
        Iterator<Users> u = usersRepository.findAll().iterator();
        for (Iterator<Users> it = u; it.hasNext(); ) {
            try {
                UserModel um = new UserModel();
                Users u1 = it.next();
                long userId = u1.getUserId();
                System.out.println("getAllUsers userId : " + userId);
                PersonalDetails pd = personalDetailsRepository.getPersonalDetails(userId);
                AddressUser au = addressUserRepository.getAdminAddress(userId);
                um.setUserName(u1.getUserName());
                um.setRole(pd.getRole());
                um.setEmail(pd.getEmail());
                um.setContactNo(au.getContactNo());
                um.setUserId(userId+"");
                userModels.add(um);
            } catch (Exception e) {}
        }
        return userModels;
    }
    public ResponseEntity<?> deleteGeneralUserById(Long userId) {
        usersRepository.deleteById(userId);
        return null;
    }
}