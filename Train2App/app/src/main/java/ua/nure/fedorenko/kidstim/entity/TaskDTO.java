package ua.nure.fedorenko.kidstim.entity;

import java.io.Serializable;
import java.util.List;

public class TaskDTO implements Serializable {
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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public List<ChildDTO> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskDTO)) return false;

        TaskDTO taskDTO = (TaskDTO) o;

        if (getCreationDate() != taskDTO.getCreationDate()) return false;
        if (getExpirationDate() != taskDTO.getExpirationDate()) return false;
        if (getPoints() != taskDTO.getPoints()) return false;
        if (getChildren() != null ? !getChildren().equals(taskDTO.getChildren()) : taskDTO.getChildren() != null)
            return false;
        if (!getId().equals(taskDTO.getId())) return false;
        if (getDescription() != null ? !getDescription().equals(taskDTO.getDescription()) : taskDTO.getDescription() != null)
            return false;
        if (getStatus() != taskDTO.getStatus()) return false;
        return getParent() != null ? getParent().equals(taskDTO.getParent()) : taskDTO.getParent() == null;
    }

    @Override
    public int hashCode() {
        int result = getChildren() != null ? getChildren().hashCode() : 0;
        result = 31 * result + getId().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (int) (getCreationDate() ^ (getCreationDate() >>> 32));
        result = 31 * result + (int) (getExpirationDate() ^ (getExpirationDate() >>> 32));
        result = 31 * result + getPoints();
        result = 31 * result + (getParent() != null ? getParent().hashCode() : 0);
        return result;
    }

    public List<ChildDTO> getChildren() {

        return children;
    }

    public void setChildren(List<ChildDTO> children) {
        this.children = children;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public long getExpirationDate() {
        return expirationDate;
    }

    public int getPoints() {
        return points;

    }

    public void setPoints(int points) {
        this.points = points;
    }


    public ParentDTO getParent() {
        return parent;
    }

    public void setParent(ParentDTO parent) {
        this.parent = parent;
    }

    private String id;
    private String description;
    private TaskStatus status;
    private long creationDate;

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }

    private long expirationDate;
    private int points;
    private ParentDTO parent;

}
