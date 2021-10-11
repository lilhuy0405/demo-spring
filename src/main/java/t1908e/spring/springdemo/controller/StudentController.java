package t1908e.spring.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import t1908e.spring.springdemo.dto.StudentDTO;
import t1908e.spring.springdemo.entity.Student;
import t1908e.spring.springdemo.service.StudentService;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/students")
public class StudentController {

    @Autowired
    StudentService service = new StudentService();

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public Iterable<Student> findByName(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "status", required = false) int status, @RequestParam(name = "page", required = false) int page, @RequestParam(name = "limit", required = false) int limit) {
        return service.searchPageable(name, status, page, limit);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<StudentDTO> getList() {
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public StudentDTO getDetail(@PathVariable int id) {
        return service.find(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public StudentDTO create(@RequestBody StudentDTO student) {
        return service.create(student);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public StudentDTO update(@PathVariable int id, @RequestBody StudentDTO student) {
        StudentDTO edit = service.edit(id, student);
        if (edit == null) {
            System.out.println("null");
        }
        return edit;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public boolean delete(@PathVariable int id) {
        return service.delete(id);
    }
}
