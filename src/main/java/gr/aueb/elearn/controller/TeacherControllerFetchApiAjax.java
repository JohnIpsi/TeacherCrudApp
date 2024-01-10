package gr.aueb.elearn.controller;

import gr.aueb.elearn.dto.TeacherDTO;
import gr.aueb.elearn.service.impl.TeacherServiceImpl;
import gr.aueb.elearn.service.exceptions.TeacherIdAlreadyExistsException;
import gr.aueb.elearn.service.exceptions.TeacherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TeacherControllerFetchApiAjax {

    private final TeacherServiceImpl teacherService;

    public TeacherControllerFetchApiAjax(TeacherServiceImpl teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping("/fetchApiAjax")
    public String fetchApiAjaxPage(Model model) {
        List<TeacherDTO> teachersModel = teacherService.getAllTeachers();
        model.addAttribute("teachersModel", teachersModel);
        return "/fetchApiAjax";

    }

    @PostMapping("/addRecord")
    public String deleteRecord(@RequestParam("surname") String surname, @RequestParam("firstName") String firstname) throws TeacherIdAlreadyExistsException {

        TeacherDTO teacherToAdd = new TeacherDTO();
        teacherToAdd.setFirstName(firstname);
        teacherToAdd.setSurname(surname);
        teacherService.insertTeacher(teacherToAdd);

        return "redirect:/fetchApiAjax";
    }

    @PostMapping("/deleteRecord")
    public String deleteRecord(@RequestParam("teacherId") int teacherId) throws TeacherNotFoundException {

        TeacherDTO teacherToDelete = new TeacherDTO();
        teacherToDelete.setId(teacherId);
        teacherService.deleteTeacher(teacherToDelete);

        return "redirect:/fetchApiAjax";
    }


    @PostMapping("/updateRecord")
    public String updateRecord(@RequestParam("teacherId") int teacherId, @RequestParam("surname") String surname, @RequestParam("firstName") String firstname) throws TeacherNotFoundException {

        TeacherDTO oldTeacher = new TeacherDTO();
        oldTeacher.setId(teacherId);

        TeacherDTO newTeacher = new TeacherDTO();
        newTeacher.setFirstName(firstname);
        newTeacher.setSurname(surname);
        teacherService.updateTeacher(oldTeacher, newTeacher);

        return "redirect:/fetchApiAjax";
    }


    @GetMapping("/searchRecord")
    public ResponseEntity<List<TeacherDTO>> searchRecord(@RequestParam(name = "searchField") String searchField) {
        List<TeacherDTO> filteredData = teacherService.getFilteredTeachers(searchField);
        return new ResponseEntity<>(filteredData, HttpStatus.OK);
    }

}
