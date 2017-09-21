package ua.nure.fedorenko.kidstim.service;

import ua.nure.fedorenko.kidstim.model.entity.Child;

public interface ChildService {

    void addChild(Child child);

    void deleteChild(Child child);

    Child getChildById(String id);

    Child getChildByEmail(String email);

    Child minusPoints(Child child, int numberOfPoints);

    Child plusPoints(Child child, int numberOfPoints);

    Child updateChild(Child child);


}
