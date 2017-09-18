package ua.nure.fedorenko.kidstim.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parent")
public class Parent implements Serializable {


    public Parent(String id, String email, String password, String name, String surname) {
        this(email, password, name, surname);
        this.id = id;
    }

    public Parent(String email, String password, String name, String surname) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        children = new ArrayList<>();
    }

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

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "parent_child", joinColumns = {@JoinColumn(name = "parentId", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "childId", referencedColumnName = "id")})
    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    private String id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private List<Child> children;



}
