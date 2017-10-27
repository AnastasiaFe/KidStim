package ua.nure.fedorenko.kidstim.service;

import ua.nure.fedorenko.kidstim.model.entity.Task;
import ua.nure.fedorenko.kidstim.service.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    TaskDTO getTaskById(String id);

    void addTask(TaskDTO task);

    TaskDTO updateTask(TaskDTO task);

    void deleteTask(String id);

    List<TaskDTO> getTasksByParent(String parent);
}
