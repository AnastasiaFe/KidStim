package ua.nure.fedorenko.kidstim.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Reward implements Serializable {

    private String id;
    private String description;
    private int points;
    private RewardStatus status;
    private List<Child> children;
    private Parent parent;


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    public RewardStatus getStatus() {
        return status;
    }

    public void setStatus(RewardStatus status) {
        this.status = status;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "points")
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @JoinTable(name = "reward_child", joinColumns = {@JoinColumn(name = "rewardId", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "childId", referencedColumnName = "id")})
    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    @ManyToOne
    @JoinColumn(name = "parent",referencedColumnName = "id")
    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
