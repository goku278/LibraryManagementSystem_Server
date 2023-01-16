package com.library.Library.Controller;

import com.library.Library.Services.AdminServices;
import com.library.Library.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("lms/api/admin/")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    AdminServices adminServices;
    @PostMapping("register/")
    public Status register(@RequestBody UsersRegisterModel usersRegisterModel) {
        return adminServices.register(usersRegisterModel);
    }
    @PostMapping("login/{userId}/")
    public Status login(@RequestBody UsersRegisterModel usersRegisterModel, @PathVariable Long userId) {
        return adminServices.login(usersRegisterModel, userId);
    }
    @PutMapping("library/")
    public Status addLibrary(@RequestBody LibraryAdminModel lm) {
        return adminServices.addLibraryy(lm);
    }
    @PutMapping("library/librarian/link/{libraryId}/")
    public Status linkLibrarianToLibrary(@PathVariable Long libraryId, @RequestBody LibrarianModel librarianModel) {
        return adminServices.linkLibrarianToLibrary(libraryId, librarianModel);
    }
    @GetMapping("libraries/")
    public List<LibraryModel> viewAllLibraries() {
        return adminServices.viewAllLibraries();
    }
    @GetMapping("librarians/")
    public List<AdminLibrarianView> viewAllLibrarians() {
        System.out.println("Visited viewAllLibrarians...");
        return adminServices.viewAllLibrarians();
    }
    @GetMapping("profile/{adminId}/")
    public AdminProfile getAdminProfile(@PathVariable Long adminId)  {
        System.out.println("Admin profile invoked!!");
        return adminServices.getAdminProfile(adminId);
    }
    @PutMapping("update/profile/{adminId}/")
    public String updateAdmin(@RequestBody UpdateAdminModel updateAdminModel, @PathVariable Long adminId) {
        return adminServices.updateAdminProfile(updateAdminModel, adminId);
    }
    @GetMapping("allUsers/view/")
    public Set<UserModel> getAllUsers() {
        return adminServices.getAllUsers();
    }
    @DeleteMapping("library/remove/{libraryId}/")
    public Status deleteLibrary(@PathVariable Long libraryId) {
        return adminServices.deleteLibrary(libraryId);
    }
    @DeleteMapping("librarian/remove/{librarianId}/")
    public Status deleteLibrarian(@PathVariable Long librarianId) {
        return adminServices.deleteLibrarian(librarianId);
    }
    @DeleteMapping("remove/GeneralUser/{userId}/")
    public ResponseEntity<?> deleteGeneralUser(@PathVariable Long userId) {
        return adminServices.deleteGeneralUserById(userId);
    }
    @DeleteMapping("library/removeAll/")
    public Status removeAllLibrary() {
        return adminServices.deleteAllLibrary();
    }
    @DeleteMapping("librarian/removeAll/")
    public Status removeAllLibrarian() {
        return adminServices.deleteAllLibrarian();
    }
}