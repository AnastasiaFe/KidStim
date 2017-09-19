package ua.nure.fedorenko.kidstim.model.dao;

import ua.nure.fedorenko.kidstim.model.entity.Reward;

public interface RewardDao {

    /**
     * returns reward with particular id
     *
     * @param id id of the reward to search for
     * @return reward with particular id if it exists, otherwise return null
     */
    Reward getRewardById(String id);

    /**
     * adds reward to database
     *
     * @param reward reward to be added
     */
    void addReward(Reward reward);

    /**
     * updates reward's information
     *
     * @param reward reward which information should be updated
     * @return updated reward's entity
     */
    Reward updateReward(Reward reward);

    /**
     * deletes reward
     *
     * @param reward reward to be deleted
     */
    void deleteReward(Reward reward);

}
