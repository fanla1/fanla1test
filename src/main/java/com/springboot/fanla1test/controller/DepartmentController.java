package com.springboot.fanla1test.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fanla1test.exception.ResourceNotFoundException;
import com.springboot.fanla1test.model.Department;
import com.springboot.fanla1test.repository.DepartmentRepository;

@RestController
@RequestMapping("/api")
public class DepartmentController {
	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping("/dept")
	public List<Department> getAllDepartment() {
		return departmentRepository.findAll();
	}

	@GetMapping("/dept/{depId}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable(value = "depId") String depId)
			throws ResourceNotFoundException {
		Department department = departmentRepository.findById(depId)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found for this depId :: " + depId));
		return ResponseEntity.ok().body(department);
	}

	@PostMapping("/dept")
	public Department createDepartment(@Valid @RequestBody Department department) {
		return departmentRepository.save(department);
	}

	@PutMapping("/dept/{depId}")
	public ResponseEntity<Department> updateDepartment(@PathVariable(value = "depId") String depId,
			@Valid @RequestBody Department departmentDetails) throws ResourceNotFoundException {
		Department department = departmentRepository.findById(depId)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found for this depId :: " + depId));

		department.setDepId(departmentDetails.getDepId());
		department.setDepName(departmentDetails.getDepName());
		final Department updatedDepartment = departmentRepository.save(department);
		return ResponseEntity.ok(updatedDepartment);
	}

	@DeleteMapping("/dept/{depId}")
	public Map<String, Boolean> deleteDepartment(@PathVariable(value = "depId") String depId)
			throws ResourceNotFoundException {
		Department department = departmentRepository.findById(depId)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found for this depId :: " + depId));

		departmentRepository.delete(department);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@GetMapping("/dept/new")
	public List<Department> createDepartment() {
		List<Department> list = Arrays.asList(new Department("101", "101DEPT"), new Department("102", "102DEPT"));

		departmentRepository.saveAll(list);
		return departmentRepository.findAll();
	}
}
