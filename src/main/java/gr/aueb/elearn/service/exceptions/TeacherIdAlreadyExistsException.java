package gr.aueb.elearn.service.exceptions;

import gr.aueb.elearn.entity.Teacher;

public class TeacherIdAlreadyExistsException extends Exception {

    private static final long serialVersionUID = 1L;

    public TeacherIdAlreadyExistsException(Teacher teacher) {
        super("Teacher with id = " + teacher.getId() + " already exist");
    }

}