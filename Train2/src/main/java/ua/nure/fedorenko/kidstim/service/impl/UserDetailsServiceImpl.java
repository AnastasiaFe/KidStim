package ua.nure.fedorenko.kidstim.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.nure.fedorenko.kidstim.model.dao.ChildDao;
import ua.nure.fedorenko.kidstim.model.dao.ParentDao;
import ua.nure.fedorenko.kidstim.model.entity.Child;
import ua.nure.fedorenko.kidstim.model.entity.Parent;

import java.util.Collections;

public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private ParentDao parentDao;

    @Autowired
    private ChildDao childDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LOGGER.info("Load user by username is working...");
        LOGGER.info(s);
        Parent parent = parentDao.getParentByEmail(s);
        LOGGER.info(parent);
        if (parent == null) {
            LOGGER.info("Parent is null");
            Child child = childDao.getChildByEmail(s);
            if (child == null) {
                LOGGER.info("Child is null");
                throw new UsernameNotFoundException(s);
            } else {
                return new User(child.getEmail(), child.getPassword(), Collections.emptyList());
            }
        }
        LOGGER.info("PArent: " + parent.getEmail());
        return new User(parent.getEmail(), parent.getPassword(), Collections.emptyList());
    }
}
