package ua.nure.fedorenko.kidstim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.fedorenko.kidstim.model.entity.Task;
import ua.nure.fedorenko.kidstim.service.TaskService;

import java.util.List;

@RestController
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/updateTask", method = RequestMethod.PUT)
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        Task updatedTask = taskService.updateTask(task);
        if (updatedTask == null) {
            return new ResponseEntity<>(task, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteTask", method = RequestMethod.DELETE)
    public ResponseEntity<Task> deleteTask(@RequestBody Task task) {
        taskService.deleteTask(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }


    @RequestMapping(value = "/task", method = RequestMethod.GET)
    public ResponseEntity getTaskById(@RequestParam("id") String id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity("No task found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(value = "/tasksByParent", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getTasksByParent(@RequestParam("id") String parentId) {
        List<Task> tasks = taskService.getTasksByParent(parentId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}
