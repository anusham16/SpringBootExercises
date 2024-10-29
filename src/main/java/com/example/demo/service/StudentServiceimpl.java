package com.example.demo.service;

import com.example.demo.entity.Authorities;
import com.example.demo.entity.Student;
import com.example.demo.entity.Users;
import com.example.demo.exceptions.StudentNotFoundException;
import com.example.demo.repository.AuthoritiesRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceimpl {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthoritiesRepository authoritiesRepository;


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) throws StudentNotFoundException {
        return studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException("Student not found with id: " + id));

    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public String deleteStudent(int id) throws StudentNotFoundException {

        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            studentRepository.deleteById(id);
            return "Student deleted successfully";
        }
        throw new StudentNotFoundException("Student not found with id: " + id);

    }

    public Student updateStudent(Student updatedStudent, int id) {
        return studentRepository.save(updatedStudent);
    }

    public Users getUserByUsername(String username) {
        return usersRepository.findById(username).orElse(null);
    }

    public Authorities getAuthoritiesByUsername(String username) {
        return authoritiesRepository.findById(username).orElse(null);
    }


}
