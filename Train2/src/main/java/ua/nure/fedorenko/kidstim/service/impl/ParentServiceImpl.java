package ua.nure.fedorenko.kidstim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.fedorenko.kidstim.model.dao.ParentDao;
import ua.nure.fedorenko.kidstim.model.entity.Child;
import ua.nure.fedorenko.kidstim.model.entity.Parent;
import ua.nure.fedorenko.kidstim.service.ParentService;
import ua.nure.fedorenko.kidstim.service.dto.ChildDTO;
import ua.nure.fedorenko.kidstim.service.dto.ParentDTO;
import ua.nure.fedorenko.kidstim.service.mapper.ParentMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ParentServiceImpl implements ParentService {

    @Autowired
    ParentDao parentDao;
    @Autowired
    ParentMapper parentMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void addParent(ParentDTO parent) {
        parent.setPassword(bCryptPasswordEncoder.encode(parent.getPassword()));
        parent.setChildren(new ArrayList<>());
        parentDao.addParent(parentMapper.getParent(parent));
    }

    @Override
    public ParentDTO getParentById(String id) {
        return parentMapper.getParentDTO(parentDao.getParentById(id));
    }

    @Override
    public ParentDTO getParentByEmail(String email) {
        return parentMapper.getParentDTO(parentDao.getParentByEmail(email));
    }

    @Override
    public ParentDTO updateParent(ParentDTO parent) {
        return parentMapper.getParentDTO(parentDao.updateParent(parentMapper.getParent(parent)));
    }

    @Override
    public List<ChildDTO> getParentsChildren(String email) {
        ParentDTO p = getParentByEmail(email);
        return p.getChildren();
    }
}
