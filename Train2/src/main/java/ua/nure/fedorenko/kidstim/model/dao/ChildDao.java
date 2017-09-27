package ua.nure.fedorenko.kidstim.model.dao;

import ua.nure.fedorenko.kidstim.model.entity.Child;

public interface ChildDao {

    Child getChildById(String id);

    Child getChildByEmail(String email);

    Child updateChild(Child child);

    void addChild(Child child);

    void deleteChild(Child child);

}
