package com.springboot.fanla1test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.springboot.fanla1test.controller.DepartmentController;
import com.springboot.fanla1test.model.Department;
import com.springboot.fanla1test.model.Employee;
import com.springboot.fanla1test.repository.DepartmentRepository;

public class TestDepartmentManager {
	@Mock
	DepartmentRepository departmentRepository;

	@InjectMocks
	DepartmentController departmentController;

	@Before(value = "")
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllDeptTest() {
		List<Department> list = Arrays.asList(new Department("101", "101DEPT"), new Department("102", "102DEPT"));

		when(departmentRepository.findAll()).thenReturn(list);

		List<Department> resultDept = departmentController.getAllDepartment();

		assertEquals(2, resultDept.size());

	}

	@Test
	public void saveDepartmentTest() {
		Department dept = new Department("101", "101DEPT");
		when(departmentRepository.save(dept)).thenReturn(dept);
		Department resultDept = departmentRepository.save(dept);
		assertEquals("101", resultDept.getDepId());
		assertEquals("101DEPT", resultDept.getDepName());
	}

	@Test
	public void deleteDepartmentTest() {
		Department dept = new Department("101", "101DEPT");
		departmentRepository.delete(dept);
		verify(departmentRepository, times(1)).delete(dept);
	}
}
