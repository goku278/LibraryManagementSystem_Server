package com.library.Library.Controller;

import com.library.Library.Services.LibraryServices;
import com.library.Library.db.Library;
import com.library.Library.models.LibraryAdminModel;
import com.library.Library.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/library/")
public class LibraryController {
    @Autowired
    LibraryServices libraryServices;
    @PostMapping("add/")
    public Status addLibrary(@RequestBody LibraryAdminModel lm) {
        return libraryServices.addLibrary(lm);
    }
}