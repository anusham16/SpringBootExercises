package com.example.demo;

import com.example.demo.controller.StudentController;
import com.example.demo.entity.Student;
import com.example.demo.exceptions.StudentNotFoundException;
import com.example.demo.service.StudentServiceimpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestsForController {


    @Mock
    private StudentServiceimpl studentServiceimpl;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sayHello_returnsHello() {
        String response = studentController.sayHello();
        assertEquals("Hello", response);
    }

    @Test
    void getAllStudents_returnsListOfStudents() {
        //List<Student> students = Arrays.asList(new Student(), new Student());
        List<Student> students=Arrays.asList(new Student("jane.doe@example.com","anusha",1,"malyala","76877757576"),new Student("jane.doe@example.com","anusha",2,"malyala","76877757576"));
        when(studentServiceimpl.getAllStudents()).thenReturn(students);

        List<Student> response = studentController.getAllStudents();

        assertEquals(students, response);
    }

    @Test
    void getStudentById_returnsStudent() throws StudentNotFoundException {
        int studentId = 1;
       // Student student = new Student();
        Student student = new Student("anu@gmail.com","anusha",1,"malyala","76877757576");

        when(studentServiceimpl.getStudentById(studentId)).thenReturn(student);

        ResponseEntity<Student> response = studentController.getStudentById(studentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(student, response.getBody());
    }

    @Test
    void getStudentById_throwsStudentNotFoundException() throws StudentNotFoundException {
        int studentId = 1;
        when(studentServiceimpl.getStudentById(studentId)).thenThrow(new StudentNotFoundException("Student not found"));

        try {
            studentController.getStudentById(studentId);
        } catch (StudentNotFoundException e) {
            assertEquals("Student not found", e.getMessage());
        }
    }

    @Test
    void createStudent_returnsCreatedStudent() {
       //Student student = new Student();
        Student student = new Student("anu@gmail.com","anusha",1,"malyala","76877757576");

        when(studentServiceimpl.createStudent(student)).thenReturn(student);

        ResponseEntity<Student> response = studentController.createStudent(student);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(student, response.getBody());
    }

    @Test
    void deleteStudent_returnsSuccessMessage() throws StudentNotFoundException {
        int studentId = 1;
        when(studentServiceimpl.deleteStudent(studentId)).thenReturn("Student deleted successfully");

        ResponseEntity<String> response = studentController.deleteStudent(studentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Student deleted successfully", response.getBody());
    }

    @Test
    void deleteStudent_throwsStudentNotFoundException() throws StudentNotFoundException {
        int studentId = 1;
        when(studentServiceimpl.deleteStudent(studentId)).thenThrow(new StudentNotFoundException("Student not found"));

        try {
            studentController.deleteStudent(studentId);
        } catch (StudentNotFoundException e) {
            assertEquals("Student not found", e.getMessage());
        }
    }

    @Test
    void updateStudent_returnsUpdatedStudent() {
        int studentId = 1;
       // Student student = new Student();
        Student student = new Student("anu@gmail.com","anusha",1,"malyala","76877757576");

        when(studentServiceimpl.updateStudent(student, studentId)).thenReturn(student);

        ResponseEntity<Student> response = studentController.updateStudent(student, studentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(student, response.getBody());
    }
}
