package com.codegym.bookspringboot.service.book;

import com.codegym.bookspringboot.model.Book;
import com.codegym.bookspringboot.model.Category;
import com.codegym.bookspringboot.service.GeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService extends GeneralService<Book> {
    Iterable<Book> findAllByCategory(Category category);
    Page<Book> findAll(Pageable pageable);
    Page<Book> findAllByNameContaining(String name, Pageable pageable);
}
