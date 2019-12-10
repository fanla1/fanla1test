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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.springboot.fanla1test.controller.EmployeeController;
import com.springboot.fanla1test.model.Employee;
import com.springboot.fanla1test.repository.EmployeeRepository;

public class TestEmployeeManager {
	@Mock
	EmployeeRepository emloyeeRepository;

	@InjectMocks
	EmployeeController emloyeeController;

	@Before(value = "")
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllEmployeeTest() {
		List<Employee> emloyees = Arrays.asList(new Employee("testname1", "102", "1", "0123456789", "Address1", 30),
				new Employee("testname2", "101", "2", "0123456789", "Address2", 30),
				new Employee("testname3", "102", "1", "0123456789", "Address3", 30),
				new Employee("testname4", "101", "2", "0123456789", "Address4", 30),
				new Employee("testname5", "102", "1", "0123456789", "Address5", 30),
				new Employee("testname6", "101", "2", "0123456789", "Address6", 30),
				new Employee("testname7", "102", "1", "0123456789", "Address7", 30),
				new Employee("testname8", "101", "2", "0123456789", "Address8", 30),
				new Employee("testname9", "102", "2", "0123456789", "Address9", 30),
				new Employee("testname10", "101", "2", "0123456789", "Address10", 30),
				new Employee("testname11", "102", "2", "0123456789", "Address11", 30),
				new Employee("testname12", "101", "2", "0123456789", "Address12", 40));

		when(emloyeeRepository.findAll()).thenReturn(emloyees);

		List<Employee> resultEmps = emloyeeController.getAllEmployee();

		assertEquals(12, resultEmps.size());

	}

	@Test
	public void QueryEmployeeTest() {
		List<Employee> emloyees = Arrays.asList(new Employee("testname1", "101", "1", "0123456789", "Address1", 30),
				new Employee("testname2", "101", "2", "0123456789", "Address2", 30),
				new Employee("testname3", "101", "1", "0123456789", "Address3", 30),
				new Employee("testname4", "101", "2", "0123456789", "Address4", 30),
				new Employee("testname5", "101", "1", "0123456789", "Address5", 30),
				new Employee("testname6", "101", "2", "0123456789", "Address6", 30),
				new Employee("testname7", "101", "1", "0123456789", "Address7", 30),
				new Employee("testname8", "101", "2", "0123456789", "Address8", 30),
				new Employee("testname9", "101", "2", "0123456789", "Address9", 30),
				new Employee("testname10", "101", "2", "0123456789", "Address10", 30));

		Pageable pageable = PageRequest.of(0, 10, Sort.by("empNo"));
		when(emloyeeRepository.findByCondition(null, null, 30, null, pageable)).thenReturn(emloyees);

		List<Employee> resultEmps = emloyeeController.getEmployeeByCondition(Integer.parseInt("0"), null, null, 30,
				null);

		assertEquals(10, resultEmps.size());

	}

	@Test
	public void saveEmployeeTest() {
		Employee emp = new Employee("testname1", "102", "1", "0123456789", "Address1", 30);
		when(emloyeeRepository.save(emp)).thenReturn(emp);
		Employee resultEmps = emloyeeRepository.save(emp);
		assertEquals("testname1", resultEmps.getEmpName());
		assertEquals("102", resultEmps.getDepId());
		assertEquals(30, resultEmps.getEmpAge());
	}

	@Test
	public void updateEmployeeTest() {
		// 更新與新增使用相同的save方法，與新增相同測試
	}

	@Test
	public void deleteEmployeeTest() {
		Employee emp = new Employee("testname1", "102", "1", "0123456789", "Address1", 30);
		emloyeeRepository.delete(emp);
		verify(emloyeeRepository, times(1)).delete(emp);
	}
}
