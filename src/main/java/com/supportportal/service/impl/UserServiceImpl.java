package com.supportportal.service.impl;

import com.supportportal.domain.User;
import com.supportportal.domain.UserPrincipal;
import com.supportportal.exception.domain.EmailExitException;
import com.supportportal.exception.domain.UsernameExitException;
import com.supportportal.repository.UserRepository;
import com.supportportal.service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@Qualifier("UserDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.supportportal.domain.User user = userRepository.findUserByUsername(username);

        if (user == null) {
            LOGGER.error("User not found by username: {}", username);
            throw new UsernameNotFoundException("User not found by username: " + username);
        } else {
            user.setLastLoginDateDisplay(user.getLastLoginDate());

            user.setLastLoginDate(new Date());
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info("Returning found user by username: {}", username);
            return userPrincipal;
        }
    }

    @Override
    public User register(String firstName, String lastName, String username, String email) {
        ValidateNewUsernameAndEmail(StringUtils.EMPTY,username, email);
        User user new User();
        user.setUserId(genrateUserId());
        String password = genratepassword();
        String encodepaswword = encodepassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setJoinDate(new Date());
        user.setPassword(encodepaswword);
        return null;
    }

    private User ValidateNewUsernameAndEmail(String currentUsername, String newUsername,String newEmail) throws UsernameExitException, EmailExitException {
        if(StringUtils.isNotBlank(currentUsername)) {
            User currentUser = findUserByUsername(currentUsername);
            if (currentUser == null) {
                throw new UsernameNotFoundException("No user found by username " + currentUsername);
            }
            User userByUsername = findUserByUsername(newUsername);
            if (userByUsername != null && !currentUser.getId().equals(userByUsername.getId())) {
                throw new UsernameExitException("Username already exists");
            }
            User userByEmail = findUserByEmail(newUsername);
            if (userByEmail != null && !currentUser.getId().equals(userByUsername.getId())) {
                throw new EmailExitException("Username already exists");
            }
            return currentUser;
        }else{
            User userByUsername = findUserByUsername(newUsername);
            if(userByUsername != null) {
                throw new UsernameExitException("Username already exists");
            }
            User userByEmail = findUserByEmail(newUsername);
            if (userByEmail != null) {
                throw new EmailExitException("Username already exists");
            }
            return null;
        }
        }


    @Override
    public List<User> getUsers() {
        return List.of();
    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }
}
