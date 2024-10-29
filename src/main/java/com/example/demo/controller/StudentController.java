package com.example.demo.controller;

import com.example.demo.entity.Authorities;
import com.example.demo.entity.Student;
import com.example.demo.entity.Users;
import com.example.demo.exceptions.StudentNotFoundException;
import com.example.demo.service.StudentServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentServiceimpl studentServiceimpl;

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentServiceimpl.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) throws StudentNotFoundException {
        return new ResponseEntity<>(studentServiceimpl.getStudentById(id), HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        studentServiceimpl.createStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

   @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) throws StudentNotFoundException {

        return new ResponseEntity<>(studentServiceimpl.deleteStudent(id),HttpStatus.OK);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable int id){
        studentServiceimpl.updateStudent(student, id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<Users> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(studentServiceimpl.getUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/authorities/{username}")
    public ResponseEntity<Authorities> getAuthoritiesByUsername(@PathVariable String username) {
        return new ResponseEntity<>(studentServiceimpl.getAuthoritiesByUsername(username), HttpStatus.OK);
    }
}
