package ua.nure.fedorenko.kidstim.model.dao;

import ua.nure.fedorenko.kidstim.model.entity.Child;
import ua.nure.fedorenko.kidstim.model.entity.Parent;

import java.util.List;

public interface ParentDao {


    void addParent(Parent parent);

    Parent getParentById(String id);

    Parent getParentByEmail(String email);

    Parent updateParent(Parent parent);

    List<Parent> getParentsByChild(Child child);
}
