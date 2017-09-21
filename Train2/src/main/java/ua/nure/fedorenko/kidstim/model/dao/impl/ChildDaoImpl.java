package ua.nure.fedorenko.kidstim.model.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.fedorenko.kidstim.model.dao.ChildDao;
import ua.nure.fedorenko.kidstim.model.entity.Child;

public class ChildDaoImpl implements ChildDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Child getChildById(String id) {
        return sessionFactory.getCurrentSession().get(Child.class, id);
    }

    @Override
    public Child getChildByEmail(String email) {
        return sessionFactory.getCurrentSession().get(Child.class, email);
    }

    @Override
    public Child updateChild(Child child) {
        sessionFactory.getCurrentSession().update(child);
        return child;
    }

    @Override
    public void addChild(Child child) {
        sessionFactory.getCurrentSession().saveOrUpdate(child);
    }

    @Override
    public void deleteChild(Child child) {
        sessionFactory.getCurrentSession().delete(child);
    }
}
