package org.myapp.service;

import org.myapp.auth.AuthUser;
import org.myapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsCustomService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = this.userRepository
                .findByUsername(username)
                .map(AuthUser::new)
                .orElseThrow(()-> new UsernameNotFoundException("User name not found: "+username));


        return user;
    }
}
