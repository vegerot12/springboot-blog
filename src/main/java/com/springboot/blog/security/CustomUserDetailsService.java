package com.springboot.blog.security;

import com.springboot.blog.entity.User;
import com.springboot.blog.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        System.out.println("Inside loadUserByUsername ");
     User user =  userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
              .orElseThrow(()-> new UsernameNotFoundException("user not found with given username or email: "+ usernameOrEmail));
        System.out.println("Inside loadUserByUsername after fetching user name and password from db "+user.getUsername()+ user.getPassword());

        Set<GrantedAuthority> authorities = user.getRoles().stream().map((role )-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        System.out.println("Inside loadUserByUsername after mapping authorities  "+authorities);

        org.springframework.security.core.userdetails.User user1 = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        System.out.println("returns user"+ user1);
    return user1; //new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
