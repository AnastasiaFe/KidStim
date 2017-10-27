package ua.nure.fedorenko.kidstim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.fedorenko.kidstim.service.TaskService;
import ua.nure.fedorenko.kidstim.service.dto.TaskDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/updateTask", method = RequestMethod.PUT)
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO task) {
        TaskDTO updatedTask = taskService.updateTask(task);
        if (updatedTask == null) {
            return new ResponseEntity<>(task, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addTask(@RequestBody TaskDTO task) {
        taskService.addTask(task);
    }

    @RequestMapping(value = "/deleteTask", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTask(@NotNull @RequestParam("id") String id) {
        taskService.deleteTask(id);
    }


    @RequestMapping(value = "/task", method = RequestMethod.GET)
    public ResponseEntity getTaskById(@NotNull @RequestParam("id") String id) {
        TaskDTO task = taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity("No task found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(value = "/tasksByParent", method = RequestMethod.GET)
    public ResponseEntity<List<TaskDTO>> getTasksByParent(@NotNull @RequestParam("id") String parentId) {
        List<TaskDTO> tasks = taskService.getTasksByParent(parentId);
        ResponseEntity<List<TaskDTO>> response = new ResponseEntity<>(tasks, HttpStatus.OK);
        return response;
    }
}
