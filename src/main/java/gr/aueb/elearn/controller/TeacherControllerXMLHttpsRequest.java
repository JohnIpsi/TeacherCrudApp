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
public class TeacherControllerXMLHttpsRequest {

    private final TeacherServiceImpl teacherService;

    public TeacherControllerXMLHttpsRequest(TeacherServiceImpl teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/XMLHttpsRequestAjax")
    public String XMLHttpsRequestAjax(Model model) {
        List<TeacherDTO> teachersModelXMLHttps = teacherService.getAllTeachers();
        model.addAttribute("teachersModel", teachersModelXMLHttps);
        return "/XMLHttpsRequestAjax";

    }

    @PostMapping("/addRecordXMLHttps")
    public String addRecordXMLHttps(@RequestParam("surname") String surname, @RequestParam("firstName") String firstname) throws TeacherIdAlreadyExistsException {

        TeacherDTO teacherToAdd = new TeacherDTO();
        teacherToAdd.setFirstName(firstname);
        teacherToAdd.setSurname(surname);
        teacherService.insertTeacher(teacherToAdd);

        return "redirect:/XMLHttpsRequestAjax";
    }

    @PostMapping("/deleteRecordXMLHttps")
    public String deleteRecordXMLHttps(@RequestParam("teacherId") int teacherId) throws TeacherNotFoundException {

        TeacherDTO teacherToDelete = new TeacherDTO();
        teacherToDelete.setId(teacherId);
        teacherService.deleteTeacher(teacherToDelete);

        return "redirect:/XMLHttpsRequestAjax";
    }


    @PostMapping("/updateRecordXMLHttps")
    public String updateRecordXMLHttps(@RequestParam("teacherId") int teacherId, @RequestParam("surname") String surname, @RequestParam("firstName") String firstname) throws TeacherNotFoundException {

        TeacherDTO oldTeacher = new TeacherDTO();
        oldTeacher.setId(teacherId);

        TeacherDTO newTeacher = new TeacherDTO();
        newTeacher.setFirstName(firstname);
        newTeacher.setSurname(surname);
        teacherService.updateTeacher(oldTeacher, newTeacher);

        return "redirect:/XMLHttpsRequestAjax";
    }


    @GetMapping("/searchRecordXMLHttps")
    public ResponseEntity<List<TeacherDTO>> searchRecordXMLHttps(@RequestParam(name = "searchField") String searchField) {
        List<TeacherDTO> filteredData = teacherService.getFilteredTeachers(searchField);
        return new ResponseEntity<>(filteredData, HttpStatus.OK);
    }

}
