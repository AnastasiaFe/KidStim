package ua.nure.fedorenko.kidstim.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.nure.fedorenko.kidstim.model.entity.ApplicationUser;
import ua.nure.fedorenko.kidstim.service.ChildService;
import ua.nure.fedorenko.kidstim.service.ParentService;

import java.util.Collections;

public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ParentService parentService;

    @Autowired
    private ChildService childService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LOGGER.info("Load user by username is working...");
        ApplicationUser user = parentService.getParentByEmail(s);
        if (user == null) {
            user = childService.getChildByEmail(s);
            if (user == null) {
                LOGGER.info("User not found!");
                throw new UsernameNotFoundException(s);
            }
        }
        return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }
}
