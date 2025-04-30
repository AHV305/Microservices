package com.ahv.ms.employeeservice.service;

import com.ahv.ms.employeeservice.dto.APIResponseDto;
import com.ahv.ms.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto saveEmployee(EmployeeDto employeeDto);
	
	APIResponseDto getEmployeeById(Long employeeId);
	
}
