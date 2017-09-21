package ua.nure.fedorenko.kidstim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.fedorenko.kidstim.model.dao.RewardDao;
import ua.nure.fedorenko.kidstim.model.entity.Reward;
import ua.nure.fedorenko.kidstim.service.RewardService;

@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    private RewardDao rewardDao;

    @Override
    public Reward getRewardById(String id) {
        return rewardDao.getRewardById(id);
    }

    @Transactional
    @Override
    public void addReward(Reward reward) {
        rewardDao.addReward(reward);
    }

    @Override
    public Reward updateReward(Reward reward) {
        return rewardDao.updateReward(reward);
    }

    @Transactional
    @Override
    public void deleteReward(Reward reward) {
        rewardDao.deleteReward(reward);
    }
}
