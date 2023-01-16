package com.library.Library.repository;
import com.library.Library.db.AddressUser;
import com.library.Library.db.Admin;
import com.library.Library.db.PersonalDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
}