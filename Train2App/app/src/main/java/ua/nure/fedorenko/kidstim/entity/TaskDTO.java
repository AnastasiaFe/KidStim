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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskDTO taskDTO = (TaskDTO) o;

        if (creationDate != taskDTO.creationDate) return false;
        if (expirationDate != taskDTO.expirationDate) return false;
        if (points != taskDTO.points) return false;
        if (!id.equals(taskDTO.id)) return false;
        if (description != null ? !description.equals(taskDTO.description) : taskDTO.description != null)
            return false;
        if (status != taskDTO.status) return false;
        return parent != null ? parent.equals(taskDTO.parent) : taskDTO.parent == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (int) (creationDate ^ (creationDate >>> 32));
        result = 31 * result + (int) (expirationDate ^ (expirationDate >>> 32));
        result = 31 * result + points;
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        return result;
    }
}
