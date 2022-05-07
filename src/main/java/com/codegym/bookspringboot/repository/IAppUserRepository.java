package com.codegym.bookspringboot.repository;

import com.codegym.bookspringboot.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByName(String name);
}
