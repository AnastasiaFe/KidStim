package ua.nure.fedorenko.kidstim.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.fedorenko.kidstim.model.entity.Child;
import ua.nure.fedorenko.kidstim.model.entity.Reward;
import ua.nure.fedorenko.kidstim.service.dto.ChildDTO;
import ua.nure.fedorenko.kidstim.service.dto.RewardDTO;

import java.util.ArrayList;
import java.util.List;

public class RewardMapper {

    @Autowired
    private ParentMapper parentMapper;

    @Autowired
    private ChildMapper childMapper;

    public RewardDTO getRewardDTO(Reward reward) {
        RewardDTO rewardDTO = new RewardDTO();
        rewardDTO.setId(reward.getId());
        rewardDTO.setDescription(reward.getDescription());
        rewardDTO.setPoints(reward.getPoints());
        rewardDTO.setStatus(reward.getStatus());
        rewardDTO.setParent(parentMapper.getParentDTO(reward.getParent()));
        List<ChildDTO> children = new ArrayList<>();
        for (Child child : reward.getChildren()) {
            children.add(childMapper.getChildDTO(child));
        }
        rewardDTO.setChildren(children);
        return rewardDTO;
    }

    public Reward getReward(RewardDTO rewardDTO) {
        Reward reward = new Reward();
        reward.setId(rewardDTO.getId());
        reward.setDescription(rewardDTO.getDescription());
        reward.setPoints(rewardDTO.getPoints());
        reward.setStatus(rewardDTO.getStatus());
        reward.setParent(parentMapper.getParent(rewardDTO.getParent()));
        List<Child> children = new ArrayList<>();
        for (ChildDTO child : rewardDTO.getChildren()) {
            children.add(childMapper.getChild(child));
        }
        reward.setChildren(children);
        return reward;
    }

}
