package com.codegym.bookspringboot.service.appuser;

import com.codegym.bookspringboot.model.AppUser;
import com.codegym.bookspringboot.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService implements IAppUserService, UserDetailsService {

    @Autowired
    private IAppUserRepository appUserRepository;

    @Override
    public Iterable<AppUser> findAll() {
        return null;
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(AppUser appUser) {

    }

    @Override
    public void remove(Long id) {

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByName(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add( appUser.getAppRole());
        UserDetails userDetails = new User(
                appUser.getName(),
                appUser.getPassword(),
                authorities
        );
        return userDetails;
    }
}

