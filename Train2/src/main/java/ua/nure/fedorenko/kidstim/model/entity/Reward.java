package ua.nure.fedorenko.kidstim.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Reward implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "points")
    private int points;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private RewardStatus status;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "child_reward", joinColumns = {@JoinColumn(name = "rewardId")}, inverseJoinColumns = {@JoinColumn(name = "childId")})
    private List<Child> children;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent")
    private Parent parent;


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

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
