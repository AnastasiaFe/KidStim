package ua.nure.fedorenko.kidstim.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.fedorenko.kidstim.model.entity.Child;
import ua.nure.fedorenko.kidstim.model.entity.Parent;
import ua.nure.fedorenko.kidstim.service.ChildService;
import ua.nure.fedorenko.kidstim.service.ParentService;

import java.lang.invoke.MethodType;

@RestController
public class UserRestController {

    private static final Logger LOGGER = Logger.getLogger(UserRestController.class);

    @Autowired
    private ParentService parentService;

    @Autowired
    private ChildService childService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Parent> register(@RequestBody Parent parent) {
        parentService.addParent(parent);
        return new ResponseEntity<>(parent, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/updateParent", method = RequestMethod.PUT)
    public ResponseEntity<Parent> updateParent(@RequestBody Parent parent) {
        Parent updatedParent = parentService.updateParent(parent);
        if (updatedParent == null) {
            return new ResponseEntity<>(parent, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedParent, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateChild", method = RequestMethod.PUT)
    public ResponseEntity<Child> updateChild(@RequestBody Child child) {
        Child updatedChild = childService.updateChild(child);
        if (updatedChild == null) {
            return new ResponseEntity<>(child, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedChild, HttpStatus.OK);
    }

    @RequestMapping(value = "/addChild", method = RequestMethod.POST)
    public ResponseEntity<Child> addChild(@RequestBody Child child) {
        childService.addChild(child);
        return new ResponseEntity<>(child, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteChild", method = RequestMethod.DELETE)
    public ResponseEntity<Child> deleteChild(@RequestBody Child child) {
        childService.deleteChild(child);
        return new ResponseEntity<>(child, HttpStatus.OK);
    }


    @RequestMapping(value = "/parent", method = RequestMethod.GET)
    public ResponseEntity getParentById(@RequestParam("id") String id) {
        System.out.println("I'm working");
        Parent parent = parentService.getParentById(id);
        if (parent == null) {
            return new ResponseEntity("No parent found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(parent, HttpStatus.OK);
    }

    @RequestMapping(value = "/child", method = RequestMethod.GET)
    public ResponseEntity getChildById(@RequestParam("id") String id) {
        Child child = childService.getChildById(id);
        if (child == null) {
            return new ResponseEntity("No child found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(child, HttpStatus.OK);
    }


}
