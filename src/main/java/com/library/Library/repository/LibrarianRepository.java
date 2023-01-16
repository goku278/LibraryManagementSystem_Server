package com.library.Library.repository;

import com.library.Library.db.Librarian;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibrarianRepository extends CrudRepository<Librarian, Long> {
    @Query(
            value = "select order_id from librarian where order_id like ?1",
            nativeQuery = true)
    List<Long> findOrder(Long orderId);

    @Query(
            value = "select * from librarian where librarian_id like ?1", nativeQuery = true
    )
    Librarian findLibrarianById(Long lId);

    @Query(
            value = "select * from librarian where librarian_id like ?1", nativeQuery = true
    )
    Librarian findLibrarianByLibrarianId(Long librarianId);
}