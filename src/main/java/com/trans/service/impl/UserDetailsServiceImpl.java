package com.trans.service.impl;

import com.trans.model.User;
import com.trans.repository.UserRepository;
import com.trans.service.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByNickname(username);

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getNickname())
                .password(user.getPassword())
                .roles("USER")
                .build();
        return userDetails;
    }
}
