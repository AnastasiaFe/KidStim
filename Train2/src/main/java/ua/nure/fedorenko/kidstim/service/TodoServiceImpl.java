package ua.nure.fedorenko.kidstim.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmichels on 8/1/14.
 */

@Service
public class TodoServiceImpl implements TodoService {

    List<String> todos = new ArrayList<String>();

    @PostConstruct
    public void setupTodo(){
        todos.add("Write better code");
        todos.add("Learn AngularJs");
        todos.add("Watch star wars again!!!");
    }

    @Override
    public List<String> allTodos() {
        return todos;
    }

    @Override
    public void addTodo(String todo) {
        if (!todos.contains(todo)){
            todos.add(todo);
        }
    }

    @Override
    public void deleteTodo(String todo) {
        if (todos.contains(todo)){
            todos.remove(todo);
        }
    }

    @Override
    public void deleteAll() {
        todos.clear();
    }


    @Override
    public void updateTodo(int position, String todo) {
        todos.set(position, todo);
    }
}
