package ua.nure.fedorenko.kidstim.controller;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.fedorenko.kidstim.model.entity.error.ErrorResponse;
import ua.nure.fedorenko.kidstim.service.ChildService;
import ua.nure.fedorenko.kidstim.service.ParentService;
import ua.nure.fedorenko.kidstim.service.dto.ChildDTO;
import ua.nure.fedorenko.kidstim.service.dto.ParentDTO;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@RestController
public class UserRestController {

    private static final Logger LOGGER = Logger.getLogger(UserRestController.class);

    @Autowired
    private ParentService parentService;

    @Autowired
    private ChildService childService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<ParentDTO> register(@RequestBody ParentDTO parent) {
        parentService.addParent(parent);
        return new ResponseEntity<>(parent, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/updateParent", method = RequestMethod.PUT)
    public ResponseEntity<ParentDTO> updateParent(@RequestBody ParentDTO parent) {
        ParentDTO updatedParent = parentService.updateParent(parent);
        if (updatedParent == null) {
            return new ResponseEntity<>(parent, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedParent, HttpStatus.OK);
    }

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String rootPath = "C:\\kidstim\\children";
                File dir = new File(rootPath);
                if (!dir.exists())
                    dir.mkdirs();
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @RequestMapping(value = "/getImage", method = RequestMethod.GET)
    public ResponseEntity<Byte[]> getImage(@RequestParam("name") String name) {
        String rootPath = "C:\\kidstim\\children";
        File serverFile = new File(rootPath
                + File.separator + name);
        try {
            byte[] bytes = IOUtils.toByteArray(new FileInputStream(serverFile));
            Byte[] b = ArrayUtils.toObject(bytes);

            return new ResponseEntity<>(b, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/updateChild", method = RequestMethod.PUT)
    public ResponseEntity<ChildDTO> updateChild(@RequestBody ChildDTO child) {
        ChildDTO updatedChild = childService.updateChild(child);
        if (updatedChild == null) {
            return new ResponseEntity<>(child, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedChild, HttpStatus.OK);
    }

    @RequestMapping(value = "/addChild", method = RequestMethod.POST)
    public ResponseEntity<ChildDTO> addChild(@RequestBody ChildDTO child) {
        childService.addChild(child);
        return new ResponseEntity<>(child, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/parent", method = RequestMethod.GET)
    public ResponseEntity getParentById(@RequestParam("id") String id) {
        ParentDTO parent = parentService.getParentById(id);
        if (parent == null) {
            return new ResponseEntity("No parent found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(parent, HttpStatus.OK);
    }

    @RequestMapping(value = "/child", method = RequestMethod.GET)
    public ResponseEntity getChildById(@RequestParam("id") String id) {
        ChildDTO child = childService.getChildById(id);
        if (child == null) {
            return new ResponseEntity("No child found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(child, HttpStatus.OK);
    }

    @RequestMapping(value = "/parentByEmail", method = RequestMethod.GET)
    public ResponseEntity getParentByEmail(@RequestParam("email") String id) {
        ParentDTO parent = parentService.getParentByEmail(id);
        if (parent == null) {
            return new ResponseEntity("No parent found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(parent, HttpStatus.OK);
    }

    @RequestMapping(value = "/childrenByParent", method = RequestMethod.GET)
    public ResponseEntity getChildrenByParent(@RequestParam("email") String id) {
        List<ChildDTO> children = parentService.getParentsChildren(id);
        if (children == null) {
            return new ResponseEntity("No parent found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(children, HttpStatus.OK);
    }

    @RequestMapping(value = "/childByEmail", method = RequestMethod.GET)
    public ResponseEntity getChildByEmail(@RequestParam("email") String id) {
        ChildDTO child = childService.getChildByEmail(id);
        if (child == null) {
            return new ResponseEntity("No child found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(child, HttpStatus.OK);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(MethodArgumentNotValidException exception) {

        String errorMsg = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(exception.getMessage());

        return new ErrorResponse(errorMsg);
    }


}
