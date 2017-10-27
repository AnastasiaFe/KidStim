package ua.nure.fedorenko.kidstim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.fedorenko.kidstim.service.RewardService;
import ua.nure.fedorenko.kidstim.service.dto.RewardDTO;

import javax.enterprise.util.Nonbinding;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class RewardRestController {

    @Autowired
    private RewardService rewardService;

    @RequestMapping(value = "/updateReward", method = RequestMethod.PUT)
    public ResponseEntity<RewardDTO> updateReward(@RequestBody RewardDTO reward) {
        RewardDTO updatedReward = rewardService.updateReward(reward);
        if (updatedReward == null) {
            return new ResponseEntity<>(reward, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedReward, HttpStatus.OK);
    }

    @RequestMapping(value = "/addReward", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addReward(@RequestBody RewardDTO reward) {
        rewardService.addReward(reward);
    }

    @RequestMapping(value = "/deleteReward", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteReward(@NotNull @RequestParam("id") String id) {
        rewardService.deleteReward(id);
    }


    @RequestMapping(value = "/reward", method = RequestMethod.GET)
    public ResponseEntity getRewardById(@NotNull @RequestParam("id") String id) {
        RewardDTO reward = rewardService.getRewardById(id);
        if (reward == null) {
            return new ResponseEntity("No reward found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(reward, HttpStatus.OK);
    }

    @RequestMapping(value = "/rewardsByParent", method = RequestMethod.GET)
    public ResponseEntity<List<RewardDTO>> getRewardsByParent(@NotNull @RequestParam("id") String parentId) {
        List<RewardDTO> rewards = rewardService.getRewardsByParent(parentId);
        return new ResponseEntity<>(rewards, HttpStatus.OK);
    }
}
