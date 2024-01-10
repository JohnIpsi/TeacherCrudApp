package gr.aueb.elearn.service.impl;

import gr.aueb.elearn.repository.TeacherRepository;
import gr.aueb.elearn.service.interfaces.ITeacherService;
import gr.aueb.elearn.service.exceptions.TeacherIdAlreadyExistsException;
import gr.aueb.elearn.service.exceptions.TeacherNotFoundException;
import gr.aueb.elearn.dto.TeacherDTO;
import gr.aueb.elearn.entity.Teacher;
import gr.aueb.elearn.util.GeneralUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    public List<TeacherDTO> getAllTeachers() {
        return convertTeacherToDTO(teacherRepository.findAll());
    }


    @Override
    public void insertTeacher(TeacherDTO teacherDTO) throws TeacherIdAlreadyExistsException {

        Teacher newTeacher = new Teacher();
        newTeacher.setId(teacherDTO.getId());
        newTeacher.setSurname(teacherDTO.getSurname());
        newTeacher.setFirstName(teacherDTO.getFirstName());

        if (teacherRepository.findById(teacherDTO.getId()) != null) {
            throw new TeacherIdAlreadyExistsException(newTeacher);
        } else {
            teacherRepository.save(newTeacher);
        }

    }


    @Override
    public void deleteTeacher(TeacherDTO teacherDTO) throws TeacherNotFoundException {
        Teacher teacherToDelete = teacherRepository.findById(teacherDTO.getId());

        if (teacherToDelete == null) {
            throw new TeacherNotFoundException(teacherDTO);
        }

        teacherRepository.delete(teacherToDelete);
    }


    @Override
    public void updateTeacher(TeacherDTO oldTeacherDTO, TeacherDTO newTeacherDTO) throws TeacherNotFoundException {
        Teacher existingTeacher = teacherRepository.findById(oldTeacherDTO.getId());

        if (existingTeacher == null) {
            throw new TeacherNotFoundException(oldTeacherDTO);
        }

        existingTeacher.setSurname(newTeacherDTO.getSurname());
        existingTeacher.setFirstName(newTeacherDTO.getFirstName());
        existingTeacher.setUpdDate(Date.from(Instant.now()));

        teacherRepository.save(existingTeacher);
    }


    @Override
    public List<TeacherDTO> getTeachersBySurname(String surname) {
        List<Teacher> foundTeacherList = teacherRepository.findBySurname(surname);
        return convertTeacherToDTO(foundTeacherList);
    }


    @Override
    public List<TeacherDTO> getFilteredTeachers(String searchField) {
        List<Teacher> filteredRecords = new ArrayList<>();

        // Filter by exact ID
        if (GeneralUtils.isInteger(searchField)) {
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setId(Integer.parseInt(searchField));
            if (!teacherRepository.findByIdContaining(searchField).isEmpty()) {
                filteredRecords.addAll(teacherRepository.findByIdContaining(searchField));
            }
        }

        // Filter by containing ID
        if (GeneralUtils.isInteger(searchField)) {
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setId(Integer.parseInt(searchField));
            if (!teacherRepository.findByIdContaining(searchField).isEmpty()) {
                filteredRecords.addAll(teacherRepository.findByIdContaining(searchField));
            }
        }

        // Filter by characters
        filteredRecords.addAll(teacherRepository.findBySurnameContainingIgnoreCaseOrFirstNameContainingIgnoreCase(searchField, searchField));

        return convertTeacherToDTO(GeneralUtils.removeDuplicateTeachers(filteredRecords));
    }


    /**
     * Converts a list of {@link Teacher} entities to a list of {@link TeacherDTO} objects.
     *
     * @param teacherList a List of {@link Teacher} entities to be converted.
     * @return a List of {@link TeacherDTO} objects representing the converted data.
     */
    private List<TeacherDTO> convertTeacherToDTO(List<Teacher> teacherList) {
        List<TeacherDTO> teacherDTOList = new ArrayList<>();

        if (!teacherList.isEmpty()) {
            for (Teacher t : teacherList) {
                TeacherDTO teacherDTO = new TeacherDTO();
                teacherDTO.setId(t.getId());
                teacherDTO.setSurname(t.getSurname());
                teacherDTO.setFirstName(t.getFirstName());
                teacherDTOList.add(teacherDTO);
            }
        }
        return teacherDTOList;
    }


}
