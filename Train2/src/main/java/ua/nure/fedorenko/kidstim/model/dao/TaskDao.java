package ua.nure.fedorenko.kidstim.model.dao;

import ua.nure.fedorenko.kidstim.model.entity.Task;

import java.util.List;

public interface TaskDao {

    Task getTaskById(String id);

    void addTask(Task task);

    Task updateTask(Task task);

    void deleteTask(Task task);

    List<Task> getTasksByParent(String parentId);
}
