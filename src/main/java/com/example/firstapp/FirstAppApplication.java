package com.example.firstapp;

import com.example.firstapp.student.Student;
import com.example.firstapp.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class FirstAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstAppApplication.class, args);
    }

//    @Bean
//    CommandLineRunner runner(StudentRepository studentRepository){
//        return args -> {
//            Student student = new Student(
//                    "Karthi",
//                    "karthi@gmail.com",
//                    LocalDate.of(1993, Month.NOVEMBER, 20)
//            );
//            studentRepository.insert(student);
//        };
//    }

}
