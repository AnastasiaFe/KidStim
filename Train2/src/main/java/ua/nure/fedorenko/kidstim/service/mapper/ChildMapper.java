package ua.nure.fedorenko.kidstim.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.fedorenko.kidstim.model.entity.Child;
import ua.nure.fedorenko.kidstim.service.dto.ChildDTO;

public class ChildMapper {

    @Autowired
    private ParentMapper parentMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private RewardMapper rewardMapper;

    public ChildDTO getChildDTO(Child child) {
        ChildDTO childDTO = new ChildDTO();
        childDTO.setId(child.getId());
        childDTO.setEmail(child.getEmail());
        childDTO.setName(child.getName());
        childDTO.setSurname(child.getSurname());
        childDTO.setPhoto(child.getPhoto());
        childDTO.setPoints(child.getPoints());
        childDTO.setPassword(child.getPassword());
        childDTO.setGender(child.getGender());
        childDTO.setDateOfBirth(child.getDateOfBirth());
        return childDTO;

    }

    public Child getChild(ChildDTO childDTO) {
        Child child = new Child();
        child.setId(childDTO.getId());
        child.setEmail(childDTO.getEmail());
        child.setPassword(childDTO.getPassword());
        child.setName(childDTO.getName());
        child.setSurname(childDTO.getSurname());
        child.setPhoto(childDTO.getPhoto());
        child.setPoints(childDTO.getPoints());
        child.setGender(childDTO.getGender());
        child.setDateOfBirth(childDTO.getDateOfBirth());
        return child;

    }
}
