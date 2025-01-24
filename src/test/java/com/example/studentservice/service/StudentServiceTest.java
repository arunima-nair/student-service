package com.example.studentservice.service;

import com.example.studentservice.entity.Student;
import com.example.studentservice.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // This ensures that mocks are initialized
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository; // Mock the repository

    @InjectMocks
    private StudentService studentService; // Inject the mock into the service

    private Student student;

    @BeforeEach
    void setUp() {
        // Create a test student object
        student = new Student();
        student.setId(1L);
        student.setName("John Doe");
    }

    @Test
    void testAddStudent() {
        // Mock the save method of the repository
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // Call the service method
        Student result = studentService.addStudent(student);

        // Verify the result
        assertNotNull(result);
        assertEquals("John Doe", result.getName());

        // Verify the interaction with the mock
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void testGetStudentById() {
        // Mock the findById method
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // Call the service method
        Student result = studentService.getStudentById(1L);

        // Verify the result
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }
}
