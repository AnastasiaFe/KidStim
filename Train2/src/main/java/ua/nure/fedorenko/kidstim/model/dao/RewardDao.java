package ua.nure.fedorenko.kidstim.model.dao;

import ua.nure.fedorenko.kidstim.model.entity.Reward;

public interface RewardDao {

    Reward getRewardById(String id);

    void addReward(Reward reward);

    Reward updateReward(Reward reward);

    void deleteReward(Reward reward);

}
