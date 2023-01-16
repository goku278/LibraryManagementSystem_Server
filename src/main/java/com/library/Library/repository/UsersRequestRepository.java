package com.library.Library.repository;

import com.library.Library.db.UsersRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRequestRepository extends CrudRepository<UsersRequest, Long> {
    @Query(
            value = "select bookk_id from users_request where librariann_id like ?1 and cancel_request_to_issue_books like ?2", nativeQuery = true
    )
    List<Long> findBooksIdByLibrarianId(Long librarianId, String NA);
}
