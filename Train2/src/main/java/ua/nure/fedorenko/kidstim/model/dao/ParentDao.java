package ua.nure.fedorenko.kidstim.model.dao;

import ua.nure.fedorenko.kidstim.model.entity.Parent;

public interface ParentDao {

    /**
     * adds new parent to database
     *
     * @param parent parent to be added
     */
    void addParent(Parent parent);

    /**
     * returns parent with particular id
     *
     * @param id id of the parent to search for
     * @return parent with particular id if he exists, otherwise return null
     */
    Parent getParentById(String id);

    /**
     * returns parent with particular email
     *
     * @param email email of the parent to search for
     * @return parent with particular email if he exists, otherwise return null
     */
    Parent getParentByEmail(String email);

    /**
     * updates parent's information
     *
     * @param parent parent whose information should be updated
     * @return updated entity of parent
     */
    Parent updateParent(Parent parent);
}
