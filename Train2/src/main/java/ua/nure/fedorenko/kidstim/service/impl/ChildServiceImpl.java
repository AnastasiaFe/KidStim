package ua.nure.fedorenko.kidstim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.fedorenko.kidstim.model.dao.ChildDao;
import ua.nure.fedorenko.kidstim.model.dao.ParentDao;
import ua.nure.fedorenko.kidstim.model.entity.Child;
import ua.nure.fedorenko.kidstim.service.ChildService;

import java.util.ArrayList;

@Service
public class ChildServiceImpl implements ChildService {

    @Autowired
    private ChildDao childDao;

    @Autowired
    private ParentDao parentDao;

    @Transactional
    @Override
    public void addChild(Child child) {
        child.setRewards(new ArrayList<>());
        child.setTasks(new ArrayList<>());
        childDao.addChild(child);
    }

    @Transactional
    @Override
    public void deleteChild(Child child) {
        parentDao.getParentsByChild(child).forEach(e -> e.getChildren().remove(child));
        child.getTasks().forEach(e -> e.getChildren().remove(child));
        child.getRewards().forEach(e -> e.getChildren().remove(child));
        childDao.deleteChild(child);
    }

    @Override
    public Child getChildById(String id) {
        return childDao.getChildById(id);
    }

    @Override
    public Child getChildByEmail(String email) {
        return childDao.getChildByEmail(email);
    }

    @Override
    public Child minusPoints(Child child, int numberOfPoints) {
        int newPoints = child.getPoints() - numberOfPoints;
        child.setPoints(newPoints);
        return childDao.updateChild(child);
    }

    @Override
    public Child plusPoints(Child child, int numberOfPoints) {
        int newPoints = child.getPoints() + numberOfPoints;
        child.setPoints(newPoints);
        return childDao.updateChild(child);
    }

    @Override
    public Child updateChild(Child child) {
        return childDao.updateChild(child);
    }
}
