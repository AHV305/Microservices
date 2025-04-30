package com.ahv.ms.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahv.ms.employeeservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
