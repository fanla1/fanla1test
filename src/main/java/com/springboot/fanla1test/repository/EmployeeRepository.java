package com.springboot.fanla1test.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.fanla1test.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value = "select e FROM Employee e,Department d WHERE e.depId = d.depId "
			+ " and ( :empNo is null or e.empNo = :empNo ) " 
			+ " and ( :empName is null or e.empName = :empName ) "
			+ " and ( :empAge is null or e.empAge = :empAge ) "
			+ " and ( :depName is null or d.depName = :depName ) "
			+ "")
	List<Employee> findByCondition(@Param("empNo") Long empNo, @Param("empName") String empName,
			@Param("empAge") int empAge,@Param("depName") String depName, Pageable pageable);
}
