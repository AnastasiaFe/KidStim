package ua.nure.fedorenko.kidstim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.fedorenko.kidstim.service.ChildService;
import ua.nure.fedorenko.kidstim.service.dto.ChildDTO;

@RestController
public class PointsRestController {

    @Autowired
    private ChildService childService;

    @RequestMapping(value = "/minusPoints", method = RequestMethod.PUT)
    public ResponseEntity<ChildDTO> minusPoints(@RequestBody ChildDTO child, @RequestParam("points") int numberOfPoints) {
        ChildDTO c = childService.minusPoints(child, numberOfPoints);
        if (c == null) {
            return new ResponseEntity<>(child, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(child, HttpStatus.OK);
    }

    @RequestMapping(value = "/plusPoints", method = RequestMethod.PUT)
    public ResponseEntity<ChildDTO> plusPoints(@RequestBody ChildDTO child, @RequestParam("points") int numberOfPoints) {
        ChildDTO c = childService.plusPoints(child, numberOfPoints);
        if (c == null) {
            return new ResponseEntity<>(child, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(child, HttpStatus.OK);
    }
}
