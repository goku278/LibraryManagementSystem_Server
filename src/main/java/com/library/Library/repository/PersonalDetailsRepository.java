package com.library.Library.repository;
import com.library.Library.db.PersonalDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PersonalDetailsRepository extends CrudRepository<PersonalDetails, Long> {
    @Query(
            value = "select * from personal_details where user_id like ?1",
            nativeQuery = true
    )
    PersonalDetails getPersonalDetails(Long userId);
    @Transactional
    @Modifying
    @Query(
            value = "update personal_details set email = ? where user_id = ?", nativeQuery = true
    )
    public void updateEmail(String email, Long userId);
    @Query(
            value = "select user_id from personal_details where email like ?1",
            nativeQuery = true
    )
    List<Long> getId(String email);
    @Query(
            value = "select user_id from personal_details where email like ?1",
            nativeQuery = true
    )
    List<Long> findEmailIfExists(String email);
    @Query(
            value = "select user_role from personal_details where user_id like ?1",
            nativeQuery = true
    )
    String findUserRoleByUserId(Long uId);
}