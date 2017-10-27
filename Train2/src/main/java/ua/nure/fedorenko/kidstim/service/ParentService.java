package ua.nure.fedorenko.kidstim.service;

import ua.nure.fedorenko.kidstim.service.dto.ChildDTO;
import ua.nure.fedorenko.kidstim.service.dto.ParentDTO;

import java.util.List;

public interface ParentService {

    void addParent(ParentDTO parent);

    ParentDTO getParentById(String id);

    ParentDTO getParentByEmail(String email);

    ParentDTO updateParent(ParentDTO parent);

    List<ChildDTO>getParentsChildren(String parent);
}
