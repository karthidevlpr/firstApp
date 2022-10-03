package com.example.firstapp.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@ResponseBody
@ControllerAdvice
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public ResponseEntity<String> addStudent(Student student) {
        Optional<Student> studentEmail = studentRepository.findStudentByEmail(student.getEmail());

        if(studentEmail.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already used");
        }
        studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student created");
    }

    public ResponseEntity<String> deleteStudent(String id) {
        boolean isExists = studentRepository.existsById(id);

        if(!isExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        studentRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student deleted");
    }

    public Optional<Student> fetchStudent(String id) {
        boolean isExists = studentRepository.existsById(id);

        if(!isExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        return studentRepository.findById(id);
    }
}
