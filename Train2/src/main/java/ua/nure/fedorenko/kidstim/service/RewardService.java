package ua.nure.fedorenko.kidstim.service;

import ua.nure.fedorenko.kidstim.service.dto.RewardDTO;

import java.util.List;

public interface RewardService {

    RewardDTO getRewardById(String id);

    void addReward(RewardDTO reward);

    RewardDTO updateReward(RewardDTO reward);

    void deleteReward(String id);

    List<RewardDTO> getRewardsByParent(String parent);


}
