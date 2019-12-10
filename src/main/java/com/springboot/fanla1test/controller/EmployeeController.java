package com.springboot.fanla1test.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fanla1test.exception.ResourceNotFoundException;
import com.springboot.fanla1test.model.Employee;
import com.springboot.fanla1test.repository.DepartmentRepository;
import com.springboot.fanla1test.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping("/emp/all")
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@GetMapping("/emp/page")
	public List<Employee> getEmployeePage(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {
		int pagesize = 3;
		Pageable pageable = PageRequest.of(pageNo, pagesize, Sort.by("empNo"));
		return employeeRepository.findAll(pageable).getContent();
	}

	@GetMapping("/emp/one/{empNo}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "empNo") Long empNo)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(empNo)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this empNo :: " + empNo));
		return ResponseEntity.ok().body(employee);
	}

	@GetMapping("/emp/query")
	public List<Employee> getEmployeeByCondition(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "empNo", required=false) Long empNo,
			@RequestParam(value = "empName", required=false) String empName,
			@RequestParam(value = "empAge", required=false) int empAge,
			@RequestParam(value = "depName", required=false) String depName) {
		int pagesize = 10;
		Pageable pageable = PageRequest.of(pageNo, pagesize, Sort.by("empNo"));
		return employeeRepository.findByCondition(empNo,empName,empAge,depName, pageable);
	}

	@PostMapping("/emp")
	public Employee createEmployee(@Valid @RequestBody Employee employee) throws ResourceNotFoundException {
		employee.setCreateDatetime(LocalDateTime.now());
		employee.setUpdateDatetime(LocalDateTime.now());

		departmentRepository.findById(employee.getDepId()).orElseThrow(() -> new ResourceNotFoundException(
				"Employee's Department ID not found for this depId :: " + employee.getDepId()));

		return employeeRepository.save(employee);
	}

	@PutMapping("/emp/{empNo}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "empNo") Long empNo,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(empNo)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this empNo :: " + empNo));

		employee.setEmpName(employeeDetails.getEmpName());
		employee.setDepId(employeeDetails.getDepId());
		employee.setEmpSex(employeeDetails.getEmpSex());
		employee.setEmpPhone(employeeDetails.getEmpPhone());
		employee.setEmpAddress(employeeDetails.getEmpAddress());
		employee.setEmpAge(employeeDetails.getEmpAge());
		employee.setUpdateDatetime(LocalDateTime.now());

		departmentRepository.findById(employee.getDepId()).orElseThrow(() -> new ResourceNotFoundException(
				"Employee's Department ID not found for this depId :: " + employee.getDepId()));

		final Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/emp/{empNo}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "empNo") Long empNo)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(empNo)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this empNo :: " + empNo));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@GetMapping("/emp/new")
	public List<Employee> createEmployees() {
		List<Employee> list = Arrays.asList(new Employee("testname1", "102", "1", "0123456789", "Address1", 30),
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

		employeeRepository.saveAll(list);
		return employeeRepository.findAll();
	}
}
