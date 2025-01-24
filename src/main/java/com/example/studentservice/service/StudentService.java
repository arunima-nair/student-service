package com.example.studentservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentservice.entity.Student;
import com.example.studentservice.repository.StudentRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @CircuitBreaker(name = "studentService", fallbackMethod = "studentServiceFallback")
    @Retry(name = "studentService")
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> getStudent(Long id) {
        return studentRepository.findById(id);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    public void deleteById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

  
    public Student studentServiceFallback(Student student, Throwable t) {
      
        System.out.println("Fallback method triggered due to exception: " + t.getMessage());
        
        return new Student(); 
    }

   
    public String studentServiceTimeoutFallback(Student student, Throwable t) {
        return "Student service timed out. Please try again later.";
    }
}
