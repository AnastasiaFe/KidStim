package ua.nure.fedorenko.kidstim.service.dto;

import java.io.Serializable;
import java.util.List;

public class ParentDTO extends UserDTO implements Serializable{

    public List<ChildDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ChildDTO> children) {
        this.children = children;
    }

    private List<ChildDTO> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParentDTO)) return false;
        if (!super.equals(o)) return false;

        ParentDTO parentDTO = (ParentDTO) o;

        return getChildren() != null ? getChildren().equals(parentDTO.getChildren()) : parentDTO.getChildren() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getChildren() != null ? getChildren().hashCode() : 0);
        return result;
    }
}
