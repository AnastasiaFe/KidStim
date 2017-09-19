package ua.nure.fedorenko.kidstim.model.dao;

import ua.nure.fedorenko.kidstim.model.entity.Child;

public interface ChildDao {

    /**
     * returns the child with particular id
     *
     * @param id id of the child to search for
     * @return child with particular id if he exists, otherwise return null
     */
    Child getChildById(String id);

    /**
     * returns the child with particular email
     *
     * @param email email of the child to search for
     * @return child with particular email if he exists, otherwise return null
     */
    Child getChildByEmail(String email);

    /**
     * updates child's information
     *
     * @param child child whose information should be updated
     * @return updated child's entity
     */
    Child updateChild(Child child);

    /**
     * adds new child to database
     *
     * @param child child to be added
     */
    void addChild(Child child);

    /**
     * deletes child
     *
     * @param child child to be deleted
     */
    void deleteChild(Child child);

}
