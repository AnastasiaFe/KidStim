package ua.nure.fedorenko.kidstim.model.dao;

import ua.nure.fedorenko.kidstim.model.entity.Reward;

import java.util.List;

public interface RewardDao {

    Reward getRewardById(String id);

    void addReward(Reward reward);

    Reward updateReward(Reward reward);

    void deleteReward(Reward reward);

    List<Reward> getRewardsByParent(String parent);

}
