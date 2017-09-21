package ua.nure.fedorenko.kidstim.service;

import ua.nure.fedorenko.kidstim.model.entity.Task;

import java.util.List;

public interface TaskService {

    Task getTaskById(String id);

    void addTask(Task task);

    Task updateTask(Task task);

    void deleteTask(Task task);

    List<Task> getTasksByParent(String parent);
}
