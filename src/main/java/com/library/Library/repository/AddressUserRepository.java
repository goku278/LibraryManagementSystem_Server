package com.library.Library.repository;

import com.library.Library.db.AddressUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AddressUserRepository extends CrudRepository<AddressUser, Long> {

    @Query(
            value = "select * from addressuser where user_id like ?1",
            nativeQuery = true)
    public AddressUser getAdminAddress(long userId);
    @Transactional
    @Modifying
    @Query(
            value = "update addressuser set contact_no = ?1 where user_id = ?2",
            nativeQuery = true
    )
    void updateContactNo(String contactNo, Long adminId);
}