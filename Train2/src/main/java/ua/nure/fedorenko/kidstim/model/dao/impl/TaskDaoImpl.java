package ua.nure.fedorenko.kidstim.model.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.fedorenko.kidstim.model.dao.TaskDao;
import ua.nure.fedorenko.kidstim.model.entity.Task;

import java.util.List;

public class TaskDaoImpl implements TaskDao {


    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Task getTaskById(String id) {
        return sessionFactory.getCurrentSession().get(Task.class, id);
    }

    @Override
    public void addTask(Task task) {
        sessionFactory.getCurrentSession().saveOrUpdate(task);
    }

    @Override
    public Task updateTask(Task task) {
        sessionFactory.getCurrentSession().update(task);
        return task;
    }

    @Override
    public void deleteTask(Task task) {
        sessionFactory.getCurrentSession().delete(task);
    }

    @Override
    public List<Task> getTasksByParent(String parentId) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Task t where parent.id=:id");
        query.setParameter("id", parentId);
        return query.getResultList();
    }
}
