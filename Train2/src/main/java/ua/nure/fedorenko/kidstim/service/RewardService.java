package ua.nure.fedorenko.kidstim.service;

import ua.nure.fedorenko.kidstim.model.entity.Reward;

public interface RewardService {

    Reward getRewardById(String id);

    void addReward(Reward reward);

    Reward updateReward(Reward reward);

    void deleteReward(Reward reward);


}
