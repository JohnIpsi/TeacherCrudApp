package gr.aueb.elearn.service.interfaces;

import gr.aueb.elearn.service.exceptions.TeacherIdAlreadyExistsException;
import gr.aueb.elearn.service.exceptions.TeacherNotFoundException;
import gr.aueb.elearn.dto.TeacherDTO;
import gr.aueb.elearn.entity.Teacher;

import java.util.List;

public interface ITeacherService {

    /**
     * Returns all Teacher objects
     *
     * @return a List that contains the results of
     * * the search, that is a List of {@link TeacherDTO}
     * * objects.
     */
    List<TeacherDTO> getAllTeachers();

    /**
     * Inserts a {@link Teacher} based on the data carried by the
     * {@link TeacherDTO}.
     *
     * @param teacherDTO DTO object that contains the data.
     * @throws TeacherIdAlreadyExistsException if any Teacher identified by their id
     *                                         needed to be inserted has been already
     *                                         inserted.
     */
    void insertTeacher(TeacherDTO teacherDTO) throws TeacherIdAlreadyExistsException;


    /**
     * Deletes a {@link Teacher} based on the data carried by the
     * {@link TeacherDTO}.
     *
     * @param teacherDTO DTO object that contains the data.
     * @throws TeacherNotFoundException if any Teacher identified by their id
     *                                  was not found.
     */
    void deleteTeacher(TeacherDTO teacherDTO) throws TeacherNotFoundException;


    /**
     * Updates a {@link Teacher} based on the data carried by the
     * {@link TeacherDTO}.
     *
     * @param oldTeacherDTO DTO object that contains the data -mainly the id-
     *                      of the {@link Teacher} that will be updated.
     * @param newTeacherDTO DTO object that contains the data of the
     *                      new {@link Teacher}.
     * @throws TeacherNotFoundException if any Teacher identified by their id
     *                                  was not found.
     */
    void updateTeacher(TeacherDTO oldTeacherDTO, TeacherDTO newTeacherDTO) throws TeacherNotFoundException;


    /**
     * Searches and gets back to the caller a list
     * of the {@link Teacher} objects identified by
     * their surname or surname's initial letters.
     *
     * @param surname a String object that contains the
     *                surname or the letters that the
     *                surname starts with, of the {@link Teacher}
     *                objects we are looking for.
     * @return a List that contains the results of
     * the search, that is a List of {@link TeacherDTO}
     * objects.
     */
    List<TeacherDTO> getTeachersBySurname(String surname);


    /**
     * Searches and gets back to the caller a list
     * of the {@link Teacher} objects based on a search field.
     *
     * @param searchField a String object representing the search field.
     * @return a List that contains the results of
     * the filtered search, that is a List of {@link TeacherDTO}
     * objects.
     */
    List<TeacherDTO> getFilteredTeachers(String searchField);


}
