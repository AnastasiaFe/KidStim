package ua.nure.fedorenko.kidstim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.fedorenko.kidstim.model.dao.TaskDao;
import ua.nure.fedorenko.kidstim.model.entity.Parent;
import ua.nure.fedorenko.kidstim.model.entity.Task;
import ua.nure.fedorenko.kidstim.service.TaskService;

import java.util.List;

@Transactional
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    public Task getTaskById(String id) {
        return taskDao.getTaskById(id);
    }

    @Override
    public void addTask(Task task) {
        taskDao.addTask(task);
    }

    @Override
    public Task updateTask(Task task) {
        return taskDao.updateTask(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskDao.deleteTask(task);
    }

    @Override
    public List<Task> getTasksByParent(String parent) {
        return taskDao.getTasksByParent(parent);
    }
}
