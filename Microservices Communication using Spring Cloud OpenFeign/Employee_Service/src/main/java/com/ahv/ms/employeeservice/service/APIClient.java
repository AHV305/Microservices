package com.ahv.ms.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ahv.ms.employeeservice.dto.DepartmentDto;

@FeignClient(url ="http://localhost:8080", value ="DEPARTMENT-SERVICE")
public interface APIClient {
	
	@GetMapping("api/departments/{department-code}")
	DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);

}
