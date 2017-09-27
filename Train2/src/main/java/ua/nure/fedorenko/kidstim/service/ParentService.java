package ua.nure.fedorenko.kidstim.service;

import ua.nure.fedorenko.kidstim.model.entity.Parent;

public interface ParentService {

    void addParent(Parent parent);

    Parent getParentById(String id);

    Parent getParentByEmail(String email);

    Parent updateParent(Parent parent);
}
