package ua.nure.fedorenko.kidstim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.fedorenko.kidstim.model.entity.Child;
import ua.nure.fedorenko.kidstim.service.ChildService;

@RestController
public class PointsRestController {

    @Autowired
    private ChildService childService;

    @RequestMapping(value = "/minusPoints", method = RequestMethod.PUT)
    public ResponseEntity<Child> minusPoints(@RequestBody Child child, @RequestParam("points") int numberOfPoints) {
        Child c = childService.minusPoints(child, numberOfPoints);
        if (c == null) {
            return new ResponseEntity<>(child, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(child, HttpStatus.OK);
    }

    @RequestMapping(value = "/plusPoints", method = RequestMethod.PUT)
    public ResponseEntity<Child> plusPoints(@RequestBody Child child, @RequestParam("points") int numberOfPoints) {
        Child c = childService.plusPoints(child, numberOfPoints);
        if (c == null) {
            return new ResponseEntity<>(child, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(child, HttpStatus.OK);
    }
}
