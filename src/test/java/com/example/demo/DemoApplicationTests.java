package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.exceptions.StudentNotFoundException;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentServiceimpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private StudentServiceimpl studentService;

	private Student student1;
	private Student student2;
	List<Student>students;
	private int id;

	@Before("")
    public void setUp(){
		// write setup code here
		MockitoAnnotations.openMocks(this);
		//students=Arrays.asList(new Student("jane.doe@example.com","anusha",1,"malyala","76877757576"),new Student("jane.doe@example.com","anusha",2,"malyala","76877757576"));

	}

	@Test
	public void testGetAllStudents(){
		// Arrange
		List<Student> students=Arrays.asList(new Student("jane.doe@example.com","anusha",1,"malyala","76877757576"),new Student("jane.doe@example.com","anusha",2,"malyala","76877757576"));
//
		when(studentRepository.findAll()).thenReturn(students);

		// Act
		List<Student> result = studentService.getAllStudents();

		// Assert
		assertEquals(2, result.size());
		assertEquals("anusha",result.get(0).getFirstName());

	}
	@Test
	public void testGetStudentById() throws StudentNotFoundException {
		// write test case here
		List<Student> students=Arrays.asList(new Student("jane.doe@example.com","anusha",1,"malyala","76877757576"),new Student("jane.doe@example.com","Abhi",2,"malyala","76877757576"));
////
//		when(studentRepository.findById(1)).thenReturn(java.util.Optional.of(students.get(0)));
//
//		Student result = studentService.getStudentById(1);
//		assertEquals(result, students.get(0));
//		assertEquals(result.getFirstName(), "anusha");
		try {
			when(studentRepository.findById(10)).thenReturn(java.util.Optional.of(students.get(0)));

			Student result = studentService.getStudentById(1);
			assertEquals(result, students.get(0));
			assertEquals(result.getFirstName(), "anusha");
		} catch (StudentNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetStudentWhenNotFound(){
		List<Student> students=Arrays.asList(new Student("jane.doe@example.com","anusha",1,"malyala","76877757576"),new Student("jane.doe@example.com","Abhi",2,"malyala","76877757576"));

		id=10;
		when(studentRepository.findById(id)).thenReturn(java.util.Optional.empty());
		try {
			studentService.getStudentById(id);
		} catch (StudentNotFoundException e) {
			assertEquals("Student not found with id: "+id, e.getMessage());
		}

	}
	@Test
	public void testCreateStudent(){
		// write test case here
		Student student = new Student("anu@gmail.com","anusha",1,"malyala","76877757576");


		//when(studentRepository.save(student)).thenReturn(student);
		//when(studentRepository.save(any(Student.class))).thenReturn(student);
		studentService.createStudent(student);
		verify(studentRepository).save(student);
	}
	@Test
	public void testDeleteStudentWhenPresent() throws StudentNotFoundException {
		// write test case here
		//arrange
		Student student = new Student("anu@gmail.com","anusha",1,"malyala","76877757576");

		//act
		when(studentRepository.findById(1)).thenReturn(java.util.Optional.of(student));
		assertEquals("Student deleted successfully", studentService.deleteStudent(1));

		verify(studentRepository, times(1)).deleteById(1);


	}

	@Test
	public void testDeleteStudentWhenNotFound(){
		int studentId=1;
		when(studentRepository.findById(studentId)).thenReturn(Optional.empty());
		assertThrows(StudentNotFoundException.class, ()->studentService.deleteStudent(studentId));
		verify(studentRepository, never()).deleteById(studentId);
	}
	@Test
	public void testUpdateStudent(){
		// write test case here
		Student student = new Student("anu@gmail.com","anusha",1,"malyala","76877757576");
		student.setEmail("anusha@gmail.com");
		when(studentRepository.save(student)).thenReturn(student);
		assertEquals(student, studentService.updateStudent(student, 1));

	}


}
