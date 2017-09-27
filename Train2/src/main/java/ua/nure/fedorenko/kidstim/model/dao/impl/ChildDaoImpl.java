package ua.nure.fedorenko.kidstim.model.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Child where email=:email");
        query.setParameter("email", email);
        if (query.getResultList().size() != 0) {
            return (Child) query.getResultList().get(0);
        }
        return null;
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
