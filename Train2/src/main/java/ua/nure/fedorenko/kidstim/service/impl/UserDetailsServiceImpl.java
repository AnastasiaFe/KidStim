package ua.nure.fedorenko.kidstim.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.nure.fedorenko.kidstim.service.ChildService;
import ua.nure.fedorenko.kidstim.service.ParentService;
import ua.nure.fedorenko.kidstim.service.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ParentService parentService;

    @Autowired
    private ChildService childService;

    private static final String PARENT_ROLE = "parent";
    private static final String CHILD_ROLE = "child";

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LOGGER.info("Load user by username is working...");
        List<GrantedAuthority> authorityList = new ArrayList<>();
        UserDTO user = parentService.getParentByEmail(s);
        if (user == null) {
            user = childService.getChildByEmail(s);
            if (user == null) {
                LOGGER.info("User not found!");
                throw new UsernameNotFoundException(s);
            } else {
                authorityList.add(new SimpleGrantedAuthority(CHILD_ROLE));
            }
        } else {
            authorityList.add(new SimpleGrantedAuthority(PARENT_ROLE));
        }
        return new User(user.getEmail(), user.getPassword(), authorityList);
    }
}
