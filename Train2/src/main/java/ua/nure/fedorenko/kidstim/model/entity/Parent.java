package ua.nure.fedorenko.kidstim.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "parent")
public class Parent extends ApplicationUser implements Serializable {

    public Parent() {

    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @JoinTable(name = "parent_child", joinColumns = {@JoinColumn(name = "parentId")}, inverseJoinColumns = {@JoinColumn(name = "childId")})
    private List<Child> children;

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

}
