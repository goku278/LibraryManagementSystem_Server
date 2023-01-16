package com.library.Library.Services;

import com.library.Library.config.Constants;
import com.library.Library.db.Library;
import com.library.Library.models.LibraryAdminModel;
import com.library.Library.models.Status;
import com.library.Library.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryServices {
    @Autowired
    LibraryRepository libraryRepository;
    public Status addLibrary(LibraryAdminModel lm) {
        Library l = new Library();
        l.setLibraryName(lm.getLibraryName());
        libraryRepository.save(l);
        return new Status(200, Constants.LIBRARY_ADDED_SUCCESSFULLY);
    }
}
