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

    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(students);
    }

    public ResponseEntity<Student> addStudent(Student student) {

        Optional<Student> studentEmail = studentRepository.findStudentByEmail(student.getEmail());

        if(studentEmail.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already used");
        }
        Student student1 = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student1);
    }

    public ResponseEntity<String> deleteStudent(String id) {
        boolean isExists = studentRepository.existsById(id);

        if(!isExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        studentRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student deleted");
    }

    public ResponseEntity<Student> fetchStudent(String id) {
        Optional<Student> student = studentRepository.findById(id);

        if(student.isPresent()){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(student.get());
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
    }

    public ResponseEntity<Student> updateStudent(String id, Student student) {
        Optional<Student> studentData = studentRepository.findById(id);
        if(studentData.isPresent()){
            Student student1 = studentData.get();
            student1.setName(student.getName());
            student1.setDob(student.getDob());
            student1.setEmail(student.getEmail());
            Student student2 = studentRepository.save(student1);
            return ResponseEntity.status(HttpStatus.CREATED).body(student2);

        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
    }
}
