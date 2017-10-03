package ua.nure.fedorenko.kidstim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.fedorenko.kidstim.model.entity.Reward;
import ua.nure.fedorenko.kidstim.service.RewardService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class RewardRestController {

    @Autowired
    private RewardService rewardService;

    @RequestMapping(value = "/updateReward", method = RequestMethod.PUT)
    public ResponseEntity<Reward> updateReward(@RequestBody Reward reward) {
        Reward updatedReward = rewardService.updateReward(reward);
        if (updatedReward == null) {
            return new ResponseEntity<>(reward, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedReward, HttpStatus.OK);
    }

    @RequestMapping(value = "/addReward", method = RequestMethod.POST)
    public ResponseEntity<Reward> addReward(@RequestBody Reward reward) {
        rewardService.addReward(reward);
        return new ResponseEntity<>(reward, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteReward", method = RequestMethod.DELETE)
    public ResponseEntity<Reward> deleteReward(@RequestBody Reward reward) {
        rewardService.deleteReward(reward);
        return new ResponseEntity<>(reward, HttpStatus.OK);
    }


    @RequestMapping(value = "/reward", method = RequestMethod.GET)
    public ResponseEntity getRewardById(@NotNull @RequestParam("id") String id) {
        Reward reward = rewardService.getRewardById(id);
        if (reward == null) {
            return new ResponseEntity("No reward found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(reward, HttpStatus.OK);
    }

    @RequestMapping(value = "/rewardsByParent", method = RequestMethod.GET)
    public ResponseEntity<List<Reward>> getRewardsByParent(@NotNull @RequestParam("id") String parentId) {
        List<Reward> rewards = rewardService.getRewardsByParent(parentId);
        return new ResponseEntity<>(rewards, HttpStatus.OK);
    }
}
