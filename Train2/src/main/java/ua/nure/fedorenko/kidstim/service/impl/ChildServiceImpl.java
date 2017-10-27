package ua.nure.fedorenko.kidstim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.fedorenko.kidstim.model.dao.ChildDao;
import ua.nure.fedorenko.kidstim.model.dao.ParentDao;
import ua.nure.fedorenko.kidstim.model.entity.Child;
import ua.nure.fedorenko.kidstim.service.ChildService;
import ua.nure.fedorenko.kidstim.service.dto.ChildDTO;
import ua.nure.fedorenko.kidstim.service.mapper.ChildMapper;

import java.util.ArrayList;

@Transactional
@Service
public class ChildServiceImpl implements ChildService {

    @Autowired
    private ChildDao childDao;

    @Autowired
    private ParentDao parentDao;

    @Autowired
    private ChildMapper childMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void addChild(ChildDTO child) {
        child.setPassword(bCryptPasswordEncoder.encode(child.getPassword()));
        childDao.addChild(childMapper.getChild(child));
    }

    @Override
    public ChildDTO getChildById(String id) {
        return childMapper.getChildDTO(childDao.getChildById(id));
    }

    @Override
    public ChildDTO getChildByEmail(String email) {
        return childMapper.getChildDTO(childDao.getChildByEmail(email));
    }

    @Override
    public ChildDTO minusPoints(ChildDTO child, int numberOfPoints) {
        int newPoints = child.getPoints() - numberOfPoints;
        child.setPoints(newPoints);
        return childMapper.getChildDTO(childDao.updateChild(childMapper.getChild(child)));
    }

    @Override
    public ChildDTO plusPoints(ChildDTO child, int numberOfPoints) {
        int newPoints = child.getPoints() + numberOfPoints;
        child.setPoints(newPoints);
        return childMapper.getChildDTO(childDao.updateChild(childMapper.getChild(child)));
    }

    @Override
    public ChildDTO updateChild(ChildDTO child) {
        return childMapper.getChildDTO(childDao.updateChild(childMapper.getChild(child)));
    }
}
