package com.codegym.bookspringboot.repository;

import com.codegym.bookspringboot.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends PagingAndSortingRepository<Category, Long> {
}
