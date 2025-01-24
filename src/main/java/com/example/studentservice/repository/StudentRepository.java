package com.example.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentservice.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {}
