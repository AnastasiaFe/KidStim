package ua.nure.fedorenko.kidstim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.fedorenko.kidstim.model.dao.TaskDao;
import ua.nure.fedorenko.kidstim.model.entity.Parent;
import ua.nure.fedorenko.kidstim.model.entity.Task;
import ua.nure.fedorenko.kidstim.service.TaskService;
import ua.nure.fedorenko.kidstim.service.dto.TaskDTO;
import ua.nure.fedorenko.kidstim.service.mapper.TaskMapper;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public TaskDTO getTaskById(String id) {
        return taskMapper.getTaskDTO(taskDao.getTaskById(id));
    }

    @Override
    public void addTask(TaskDTO task) {
        taskDao.addTask(taskMapper.getTask(task));
    }

    @Override
    public TaskDTO updateTask(TaskDTO task) {
        return taskMapper.getTaskDTO(taskDao.updateTask(taskMapper.getTask(task)));
    }

    @Override
    public void deleteTask(String id) {
        TaskDTO task = getTaskById(id);
        taskDao.deleteTask(taskMapper.getTask(task));
    }

    @Override
    public List<TaskDTO> getTasksByParent(String parent) {
        List<TaskDTO> tasks = new ArrayList<>();
        for (Task task : taskDao.getTasksByParent(parent)) {
            tasks.add(taskMapper.getTaskDTO(task));
        }
        return tasks;
    }
}
