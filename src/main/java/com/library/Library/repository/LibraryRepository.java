package com.library.Library.repository;

import com.library.Library.db.Library;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends CrudRepository<Library, Long> {
    @Query(
            value = "select library_id from library where library_name like ?1", nativeQuery = true
    )
    Long findLibraryIdByName(String libraryName);
}