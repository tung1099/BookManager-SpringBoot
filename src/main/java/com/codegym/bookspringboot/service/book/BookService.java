package com.codegym.bookspringboot.service.book;

import com.codegym.bookspringboot.model.Book;
import com.codegym.bookspringboot.model.Category;
import com.codegym.bookspringboot.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService{

    @Autowired
    private IBookRepository bookRepository;


    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void save(Book book) {

        bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {

        bookRepository.deleteById(id);
    }

    @Override
    public Iterable<Book> findAllByCategory(Category category) {
        return bookRepository.findAllByCategory(category);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findAllByNameContaining(String name, Pageable pageable) {
        return bookRepository.findAllByNameContaining(name,pageable);
    }
}
