package com.ahv.ms.departmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahv.ms.departmentservice.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Department findByDepartmentCode(String departmentCode);
}
