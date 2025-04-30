package com.ahv.ms.employeeservice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ahv.ms.employeeservice.dto.APIResponseDto;
import com.ahv.ms.employeeservice.dto.DepartmentDto;
import com.ahv.ms.employeeservice.dto.EmployeeDto;
import com.ahv.ms.employeeservice.entity.Employee;
import com.ahv.ms.employeeservice.repository.EmployeeRepository;
import com.ahv.ms.employeeservice.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, RestTemplate restTemplate) {
		super();
		this.employeeRepository = employeeRepository;
		this.restTemplate = restTemplate;
	}

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		
		Employee employee = new Employee(
				employeeDto.getId(),
				employeeDto.getFirstName(),
				employeeDto.getLastName(),
				employeeDto.getEmail(),
				employeeDto.getDepartmentCode()
				);
		Employee savedEmployee = employeeRepository.save(employee);
		
		EmployeeDto savedEmployeeDto = new EmployeeDto(
				savedEmployee.getId(),
				savedEmployee.getFirstName(),
				savedEmployee.getLastName(),
				savedEmployee.getEmail(),
				savedEmployee.getDepartmentCode()
				);
		
		return savedEmployeeDto;
	}

	//GET : http://localhost:8081/api/employees/3
	@Override
	public APIResponseDto getEmployeeById(Long employeeId) {
	
		Employee employee= employeeRepository.findById(employeeId).get();
		
		ResponseEntity<DepartmentDto> responseEntity =restTemplate.getForEntity
				("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
		
		DepartmentDto departmentDto = responseEntity.getBody();
		EmployeeDto employeeDto = new EmployeeDto(
				employee.getId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmail(),
				employee.getDepartmentCode()
				);
		
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setEmployee(employeeDto);
		apiResponseDto.setDepartment(departmentDto);
		
		return apiResponseDto;
	}

}
