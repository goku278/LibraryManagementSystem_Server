package com.library.Library.repository;

import com.library.Library.db.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {

    @Query(
            value = "select user_id from users where librarian_id like ?1", nativeQuery = true
    )
    Long findUserIdByLibrarianId(String librarianId);
}