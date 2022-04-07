package com.pedrolsoares.marketplace.service;

import com.pedrolsoares.marketplace.model.AppUser;
import com.pedrolsoares.marketplace.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AppUser createUser(AppUser newUser){

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("user " + username);

        Optional<AppUser> userFound = username.contains("@") ? userRepository.findByEmail(username) : userRepository.findByUser_name(username);

        AppUser appUser = userFound.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));

        return new User(username, appUser.getPassword(), new ArrayList<>());
    }
}
