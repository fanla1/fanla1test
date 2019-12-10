package com.springboot.fanla1test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.fanla1test.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, String>{

}
