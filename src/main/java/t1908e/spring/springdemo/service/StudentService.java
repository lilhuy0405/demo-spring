package t1908e.spring.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import t1908e.spring.springdemo.dto.StudentDTO;
import t1908e.spring.springdemo.entity.Student;
import t1908e.spring.springdemo.entity.StudentExtraInformation;
import t1908e.spring.springdemo.repository.StudentJPARepository;
import t1908e.spring.springdemo.repository.StudentRepository;
import t1908e.spring.springdemo.util.ConvertHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
@Service
public class StudentService {

    @Autowired
    private StudentJPARepository studentRepository;

    public Iterable<Student> findByName(String name) {
        return studentRepository.findStudentByNameContains(name);
    }
    public StudentDTO create(StudentDTO student) {
        //convert
        Student studentToSave = ConvertHelper.convertStudentDtoToStudent(student);
        studentToSave.setCreatedAt(new Date());
        studentToSave.setUpdatedAt(new Date());
        studentToSave.setStatus(1);
        studentToSave.getInformation().setStudent(studentToSave);
        Student save = studentRepository.save(studentToSave);
        if(save == null) {
            return null;
        }
        return new StudentDTO(save);
    }
    public StudentDTO edit(int id, StudentDTO studentDTO) {
        StudentDTO studentDtoExist = find(id);
        if(studentDtoExist == null) {
            return null;
        }
        //only update if not null
        if(studentDTO.getEmail() != null) {
            studentDtoExist.setEmail(studentDTO.getEmail());
        }
        if(studentDTO.getName() != null) {
            studentDtoExist.setName(studentDTO.getName());
        }
        if(studentDTO.getStatus()!= null) {
            studentDtoExist.setStatus(studentDTO.getStatus());
        }
        if(studentDTO.getIntroduction() != null) {
            studentDtoExist.setIntroduction(studentDTO.getIntroduction());
        }
        if(studentDTO.getPhone() != null) {
            studentDtoExist.setPhone(studentDTO.getPhone());
        }
        if(studentDTO.getIntroduction() != null) {
            studentDtoExist.setIntroduction(studentDTO.getIntroduction());
        }
        if(studentDTO.getAddress() != null) {
            studentDtoExist.setAddress(studentDTO.getAddress());
        }
        Student toUpdate = ConvertHelper.convertStudentDtoToStudent(studentDtoExist);
        toUpdate.setId(id);
        toUpdate.setUpdatedAt(new Date());
        toUpdate.getInformation().setStudent(toUpdate);
        Student save = studentRepository.save(toUpdate);
        return ConvertHelper.convertStudentToStudentDTO(save);
    }
    public  StudentDTO find(int id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        Student student = studentOptional.orElse(null);
        if(student == null) {
            return null;
        }
        return new StudentDTO(student);
    }
    public ArrayList<StudentDTO> getAll() {
        if(studentRepository == null) System.out.println("null");
        Iterable<Student> all = studentRepository.findAll();
        ArrayList<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();
        for (Student std :
                all) {
            studentDTOs.add(ConvertHelper.convertStudentToStudentDTO(std));
        }
        return studentDTOs;
    }
    public boolean delete(int id) {
        try {
            studentRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public Iterable<Student> search(String name, int status) {
        return studentRepository.searchStudent(name, status);
    }

    public Iterable<Student> searchPageable(String name, int status, int page, int limit) {
        return studentRepository.searchStudentPageable(name, status, PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt")));
    }
}
