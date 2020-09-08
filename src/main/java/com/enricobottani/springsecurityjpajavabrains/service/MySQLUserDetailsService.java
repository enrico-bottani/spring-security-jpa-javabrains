package com.enricobottani.springsecurityjpajavabrains.service;

import com.enricobottani.springsecurityjpajavabrains.auth.UserAccountDetails;
import com.enricobottani.springsecurityjpajavabrains.models.User;
import com.enricobottani.springsecurityjpajavabrains.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service("MySQL")
public class MySQLUserDetailsService implements UserDetailsService {

    public UserRepository userRepository;

    @Autowired
    public MySQLUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new UserAccountDetails(
                        user.getUsername(),
                        user.getPassword(),
                        Arrays.stream(user.getRoles().split(","))
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toSet()),
                        true, true, true, true))
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
    }
}
