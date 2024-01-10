package gr.aueb.elearn.service.exceptions;

import gr.aueb.elearn.dto.TeacherDTO;

public class TeacherNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public TeacherNotFoundException(TeacherDTO teacherdto) {
        super("Teacher with id = " + teacherdto.getId() + " does no exist");
    }

}
