package com.maze.solver.service;

import com.maze.solver.entity.MyUserDetails;
import com.maze.solver.entity.User;
import com.maze.solver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String username){
        return userRepository.getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }

}