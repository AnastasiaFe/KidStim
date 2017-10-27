package ua.nure.fedorenko.kidstim.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.fedorenko.kidstim.model.entity.Child;
import ua.nure.fedorenko.kidstim.model.entity.Parent;
import ua.nure.fedorenko.kidstim.service.dto.ChildDTO;
import ua.nure.fedorenko.kidstim.service.dto.ParentDTO;

import java.util.ArrayList;
import java.util.List;

public class ParentMapper {

    @Autowired
    private ChildMapper childMapper;

    public ParentDTO getParentDTO(Parent parent) {
        ParentDTO parentDTO = new ParentDTO();
        parentDTO.setId(parent.getId());
        parentDTO.setEmail(parent.getEmail());
        parentDTO.setName(parent.getName());
        parentDTO.setPassword(parent.getPassword());
       /* List<Child> children = new ArrayList<>();
        children.addAll(parent.getChildren());
        parentDTO.setChildren(children);
        */
        List<ChildDTO> children = new ArrayList<>();
        for (Child child : parent.getChildren()) {
            children.add(childMapper.getChildDTO(child));
        }
        parentDTO.setChildren(children);
        parentDTO.setSurname(parent.getSurname());
        return parentDTO;
    }

    public Parent getParent(ParentDTO parentDTO) {
        Parent parent = new Parent();
        parent.setId(parentDTO.getId());
        parent.setEmail(parentDTO.getEmail());
        parent.setPassword(parentDTO.getPassword());
        parent.setName(parentDTO.getName());
        parent.setSurname(parentDTO.getSurname());
        List<Child> children = new ArrayList<>();
        for (ChildDTO child : parentDTO.getChildren()) {
            children.add(childMapper.getChild(child));
        }
        parent.setChildren(children);
        return parent;
    }
}
