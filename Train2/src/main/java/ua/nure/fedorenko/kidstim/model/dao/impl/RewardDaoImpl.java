package ua.nure.fedorenko.kidstim.model.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.fedorenko.kidstim.model.dao.RewardDao;
import ua.nure.fedorenko.kidstim.model.entity.Reward;

public class RewardDaoImpl implements RewardDao {


    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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
        sessionFactory.getCurrentSession().delete(Reward.class);
    }
}
