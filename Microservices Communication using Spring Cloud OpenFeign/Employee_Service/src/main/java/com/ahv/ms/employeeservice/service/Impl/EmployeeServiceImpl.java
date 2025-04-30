package com.ahv.ms.employeeservice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahv.ms.employeeservice.dto.APIResponseDto;
import com.ahv.ms.employeeservice.dto.DepartmentDto;
import com.ahv.ms.employeeservice.dto.EmployeeDto;
import com.ahv.ms.employeeservice.entity.Employee;
import com.ahv.ms.employeeservice.repository.EmployeeRepository;
import com.ahv.ms.employeeservice.service.APIClient;
import com.ahv.ms.employeeservice.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private APIClient apiClient;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, APIClient apiClient) {
		super();
		this.employeeRepository = employeeRepository;
		this.apiClient = apiClient;
	}

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

		Employee employee = new Employee(employeeDto.getId(), employeeDto.getFirstName(), employeeDto.getLastName(),
				employeeDto.getEmail(), employeeDto.getDepartmentCode());
		Employee savedEmployee = employeeRepository.save(employee);

		EmployeeDto savedEmployeeDto = new EmployeeDto(savedEmployee.getId(), savedEmployee.getFirstName(),
				savedEmployee.getLastName(), savedEmployee.getEmail(), savedEmployee.getDepartmentCode());

		return savedEmployeeDto;
	}

	// GET : http://localhost:8081/api/employees/3
	@Override
	public APIResponseDto getEmployeeById(Long employeeId) {

		Employee employee = employeeRepository.findById(employeeId).get();

		DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

		EmployeeDto employeeDto = new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(),
				employee.getEmail(), employee.getDepartmentCode());

		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setEmployee(employeeDto);
		apiResponseDto.setDepartment(departmentDto);

		return apiResponseDto;
	}

}
