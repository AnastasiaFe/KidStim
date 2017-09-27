package ua.nure.fedorenko.kidstim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.fedorenko.kidstim.model.entity.Parent;
import ua.nure.fedorenko.kidstim.service.ParentService;

@RestController
public class UserController {

    @Autowired
    private ParentService parentService;

    @RequestMapping("/users")
    public @ResponseBody
    String getUsers() {
        Parent parent = new Parent();
        parent.setEmail("ggg@nure.ua");
        parent.setPassword("123456");
        parentService.addParent(parent);
        return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
                "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
    }
}
