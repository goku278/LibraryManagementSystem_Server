package com.library.Library.repository;

import com.library.Library.db.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query(
            value = "select order_id from order_books where librarian_id like ?1",
            nativeQuery = true)
    List<Long> findOrder(Long librarianId);

    @Query(
            value = "select bookk_author,bookk_title from order_books where librarian_id like ?1",
            nativeQuery = true)
    List<Object[]> findOrders(Long librarianId);

    @Query(
            value = "select order_id from order_books where bookk_title like ?1 and librarian_id not like ?2", nativeQuery = true
    )
    List<Long> findAllOrderIdsByBookTitle(String bookTitle, Long librarianId);

    @Query(
            value = "select order_id from order_books where librarian_id like ?1 and bookk_title like ?2", nativeQuery = true
    )
    List<Long> findAllOrderIdsByLibrarianIdAndBookTitle(Long librarianId, String bookTitle);

    @Query(
            value = "select count(*) from order_books where bookk_title like ?1 and bookk_author like ?2", nativeQuery = true
    )
    Long findIfMultipleDataExists(String title, String author);
}