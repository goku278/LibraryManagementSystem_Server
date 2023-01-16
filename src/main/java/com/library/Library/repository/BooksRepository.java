package com.library.Library.repository;

import com.library.Library.db.Books;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends CrudRepository<Books, Long> {
    @Query(
            value = "select librarian_id from librarian where library_id like ?1",
            nativeQuery = true)
    List<Long> findAllLibrarians(Long libraryId);
    public static final String find_books = "select books_id, library_id, librarian_id, book_author, book_title, isbn, book_type, page_no, synopsis, price_tag, books_image from books where library_id like ?1";
    @Query(
            value = find_books, nativeQuery = true
    )
    public List<Object[]>findBooksByLibraryId(Long lId);

    public static final String find_books_by_category = "select books_id, library_id, librarian_id, book_author, book_title, isbn, book_type, page_no, synopsis, price_tag, books_image from books where book_type like ?1";
    @Query(
            value = find_books, nativeQuery = true
    )
    public List<Object[]>findBooksByCategory(String category);

    @Query(
            value = "select librarian_id from books where books_id like ?1",
            nativeQuery = true
    )
    Long findLibrarianIdByBookAndUserId(Long bookId);

    @Query(
            value = "select librarian_id from books where books_id like ?1",
            nativeQuery = true
    )
    Long findByBookId(Long bookId);

    @Query(
            value = "select * from books where book_title like ?1", nativeQuery = true
    )
    Books findBookByBookTitle(String text);
}