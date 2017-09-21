package ua.nure.fedorenko.kidstim.model.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.nure.fedorenko.kidstim.model.dao.ParentDao;
import ua.nure.fedorenko.kidstim.model.entity.Child;
import ua.nure.fedorenko.kidstim.model.entity.Parent;

import java.util.List;

@Repository
public class ParentDaoImpl implements ParentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addParent(Parent parent) {
        sessionFactory.getCurrentSession().saveOrUpdate(parent);
    }

    @Override
    public Parent getParentById(String id) {
        return sessionFactory.getCurrentSession().get(Parent.class, id);
    }

    @Override
    public Parent getParentByEmail(String email) {
        return sessionFactory.getCurrentSession().get(Parent.class, email);
    }

    @Override
    public Parent updateParent(Parent parent) {
        sessionFactory.getCurrentSession().update(parent);
        return parent;
    }

    @Override
    public List<Parent> getParentsByChild(Child child) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Parent p join p.children c where c.id=:id");
        query.setParameter("id",child.getId());
        return (List< Parent>)query.getResultList();
    }
}
