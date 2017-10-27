package ua.nure.fedorenko.kidstim.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.fedorenko.kidstim.model.dao.RewardDao;
import ua.nure.fedorenko.kidstim.model.entity.Reward;

import java.util.List;

public class RewardDaoImpl implements RewardDao {


    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Reward getRewardById(String id) {
        return sessionFactory.getCurrentSession().get(Reward.class, id);
    }

    @Override
    public void addReward(Reward reward) {
        sessionFactory.getCurrentSession().saveOrUpdate(reward);
    }

    @Override
    public Reward updateReward(Reward reward) {
        sessionFactory.getCurrentSession().update(reward);
        return reward;
    }

    @Override
    public void deleteReward(Reward reward) {
        Session session = sessionFactory.getCurrentSession();
        Object findAAgain = session.merge(reward);
        session.delete(findAAgain);
    }

    @Override
    public List<Reward> getRewardsByParent(String parent) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Reward where parent.id=:id");
        query.setParameter("id", parent);
        return query.getResultList();
    }
}
