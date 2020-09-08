package com.enricobottani.springsecurityjpajavabrains.service;

import com.enricobottani.springsecurityjpajavabrains.auth.UserAccountDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("Mock")
public class MockUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new UserAccountDetails(
                "Enrico",
                new BCryptPasswordEncoder().encode("test"),
                Set.of(new SimpleGrantedAuthority("ROLE_USER")),
                true, true, true, true);
    }
}
