package ua.nure.fedorenko.kidstim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.fedorenko.kidstim.model.dao.RewardDao;
import ua.nure.fedorenko.kidstim.model.entity.Reward;
import ua.nure.fedorenko.kidstim.service.RewardService;
import ua.nure.fedorenko.kidstim.service.dto.RewardDTO;
import ua.nure.fedorenko.kidstim.service.mapper.RewardMapper;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    private RewardDao rewardDao;

    @Autowired
    private RewardMapper rewardMapper;

    @Override
    public RewardDTO getRewardById(String id) {
        return rewardMapper.getRewardDTO(rewardDao.getRewardById(id));
    }

    @Override
    public void addReward(RewardDTO reward) {
        rewardDao.addReward(rewardMapper.getReward(reward));
    }

    @Override
    public RewardDTO updateReward(RewardDTO reward) {
        return rewardMapper.getRewardDTO(rewardDao.updateReward(rewardMapper.getReward(reward)));
    }

    @Override
    public void deleteReward(String id) {
        RewardDTO reward = getRewardById(id);
        rewardDao.deleteReward(rewardMapper.getReward(reward));
    }

    @Override
    public List<RewardDTO> getRewardsByParent(String parent) {
        List<RewardDTO> rewards = new ArrayList<>();
        for (Reward reward : rewardDao.getRewardsByParent(parent)) {
            rewards.add(rewardMapper.getRewardDTO(reward));
        }
        return rewards;
    }
}
