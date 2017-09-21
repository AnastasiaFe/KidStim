package ua.nure.fedorenko.kidstim.model.dao;

import ua.nure.fedorenko.kidstim.model.entity.Parent;
import ua.nure.fedorenko.kidstim.model.entity.Task;

import java.util.List;

public interface TaskDao {
    /**
     * returns task with particular id
     *
     * @param id id of the task to search for
     * @return task with particular id if it exists, otherwise return null
     */
    Task getTaskById(String id);

    /**
     * adds task to database
     *
     * @param task task to be added
     */
    void addTask(Task task);

    /**
     * updates task's information
     *
     * @param task task which information should be updated
     * @return updated task's entity
     */
    Task updateTask(Task task);

    /**
     * deletes task
     *
     * @param task reward to be deleted
     */
    void deleteTask(Task task);


    List<Task>getTasksByParent(String parentId);
}
