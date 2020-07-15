package br.com.nms.urlshortener.service;

import br.com.nms.urlshortener.controller.payload.UserPrinciple;
import br.com.nms.urlshortener.domain.User;
import br.com.nms.urlshortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> username or email : " + userName)
                );

        return UserPrinciple.build(user);
    }

    public Optional<User> findSystemUser(String username) {
        return userRepository.findByUserName(username);
    }

    public User loadCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            User user = findSystemUser(userDetails.getUsername()).get();
            return  user;
        }

        return null;
    }

    public void updateLastLogin() {
        User user = loadCurrentUser();
        user.setLastLogin(new Date());
        userRepository.save(user);
    }

}