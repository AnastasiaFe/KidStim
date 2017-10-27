package ua.nure.fedorenko.kidstim.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.fedorenko.kidstim.model.entity.Child;
import ua.nure.fedorenko.kidstim.model.entity.Task;
import ua.nure.fedorenko.kidstim.service.dto.ChildDTO;
import ua.nure.fedorenko.kidstim.service.dto.TaskDTO;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper {


    @Autowired
    private ParentMapper parentMapper;

    @Autowired
    private ChildMapper childMapper;

    public TaskDTO getTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setCreationDate(task.getCreationDate());
        taskDTO.setExpirationDate(task.getExpirationDate());
        taskDTO.setPoints(task.getPoints());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setParent(parentMapper.getParentDTO(task.getParent()));
        List<ChildDTO> children = new ArrayList<>();
        for (Child child : task.getChildren()) {
            children.add(childMapper.getChildDTO(child));
        }
        taskDTO.setChildren(children);
        return taskDTO;
    }

    public Task getTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setDescription(taskDTO.getDescription());
        task.setCreationDate(taskDTO.getCreationDate());
        task.setExpirationDate(taskDTO.getExpirationDate());
        task.setPoints(taskDTO.getPoints());
        task.setStatus(taskDTO.getStatus());
        task.setParent(parentMapper.getParent(taskDTO.getParent()));
        List<Child> children = new ArrayList<>();
        for (ChildDTO child : taskDTO.getChildren()) {
            children.add(childMapper.getChild(child));
        }
        task.setChildren(children);
        return task;
    }



}
