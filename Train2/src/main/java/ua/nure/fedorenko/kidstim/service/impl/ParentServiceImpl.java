package ua.nure.fedorenko.kidstim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.fedorenko.kidstim.model.dao.ParentDao;
import ua.nure.fedorenko.kidstim.model.entity.Parent;
import ua.nure.fedorenko.kidstim.service.ParentService;

import java.util.ArrayList;

@Transactional
@Service
public class ParentServiceImpl implements ParentService {

    @Autowired
    ParentDao parentDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void addParent(Parent parent) {
        parent.setPassword(bCryptPasswordEncoder.encode(parent.getPassword()));
        parent.setChildren(new ArrayList<>());
        parentDao.addParent(parent);
    }

    @Override
    public Parent getParentById(String id) {
        return parentDao.getParentById(id);
    }

    @Override
    public Parent getParentByEmail(String email) {
        return parentDao.getParentByEmail(email);
    }

    @Override
    public Parent updateParent(Parent parent) {
        return parentDao.updateParent(parent);
    }
}
