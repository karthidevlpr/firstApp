package com.example.firstapp.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping(path = "{studentId}")
    public Optional<Student> fetchStudent(@PathVariable("studentId") String id) {
        return studentService.fetchStudent(id);
    }

    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);

    }
    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("studentId") String id){
         return studentService.deleteStudent(id);

    }

}
