package ua.nure.fedorenko.kidstim.entity;

import java.io.Serializable;
import java.util.List;

public class ParentDTO extends UserDTO implements Serializable {

    public ParentDTO() {
    }

    private List<ChildDTO> children;

    public List<ChildDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ChildDTO> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ParentDTO parentDTO = (ParentDTO) o;

        return children != null ? children.equals(parentDTO.children) : parentDTO.children == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (children != null ? children.hashCode() : 0);
        return result;
    }
}
