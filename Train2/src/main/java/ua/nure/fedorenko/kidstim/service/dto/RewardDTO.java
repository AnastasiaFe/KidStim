package ua.nure.fedorenko.kidstim.service.dto;

import ua.nure.fedorenko.kidstim.model.entity.RewardStatus;

import java.io.Serializable;
import java.util.List;

public class RewardDTO implements Serializable {

    private String id;
    private String description;
    private int points;
    private RewardStatus status;
    private ParentDTO parent;
    private List<ChildDTO> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RewardDTO)) return false;

        RewardDTO rewardDTO = (RewardDTO) o;

        if (getPoints() != rewardDTO.getPoints()) return false;
        if (!getId().equals(rewardDTO.getId())) return false;
        if (getDescription() != null ? !getDescription().equals(rewardDTO.getDescription()) : rewardDTO.getDescription() != null)
            return false;
        if (getStatus() != rewardDTO.getStatus()) return false;
        if (getParent() != null ? !getParent().equals(rewardDTO.getParent()) : rewardDTO.getParent() != null)
            return false;
        return getChildren() != null ? getChildren().equals(rewardDTO.getChildren()) : rewardDTO.getChildren() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getPoints();
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getParent() != null ? getParent().hashCode() : 0);
        result = 31 * result + (getChildren() != null ? getChildren().hashCode() : 0);
        return result;
    }

    public List<ChildDTO> getChildren() {

        return children;
    }

    public void setChildren(List<ChildDTO> children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public RewardStatus getStatus() {
        return status;
    }

    public void setStatus(RewardStatus status) {
        this.status = status;
    }

    public ParentDTO getParent() {
        return parent;
    }

    public void setParent(ParentDTO parent) {
        this.parent = parent;
    }

}
