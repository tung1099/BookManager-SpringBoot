package com.codegym.bookspringboot.repository;

import com.codegym.bookspringboot.model.Book;
import com.codegym.bookspringboot.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends PagingAndSortingRepository<Book, Long> {
    Iterable<Book> findAllByCategory(Category category);
    Page<Book> findAllByNameContaining(String name, Pageable pageable);

}
