package ua.nure.fedorenko.kidstim.entity;

import java.io.Serializable;
import java.util.List;

public class RewardDTO implements Serializable {

    private String id;

    private String description;

    private int points;

    private RewardStatus status;

    private ParentDTO parent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RewardStatus getStatus() {
        return status;
    }

    public void setStatus(RewardStatus status) {
        this.status = status;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RewardDTO rewardDTO = (RewardDTO) o;

        if (points != rewardDTO.points) return false;
        if (!id.equals(rewardDTO.id)) return false;
        if (description != null ? !description.equals(rewardDTO.description) : rewardDTO.description != null)
            return false;
        if (status != rewardDTO.status) return false;
        return parent != null ? parent.equals(rewardDTO.parent) : rewardDTO.parent == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + points;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        return result;
    }

    public ParentDTO getParent() {
        return parent;
    }

    public void setParent(ParentDTO parent) {
        this.parent = parent;
    }
}
