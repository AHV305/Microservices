package com.ahv.ms.departmentservice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahv.ms.departmentservice.dto.DepartmentDto;
import com.ahv.ms.departmentservice.entity.Department;
import com.ahv.ms.departmentservice.exception.DepartmentNotFoundException;
import com.ahv.ms.departmentservice.repository.DepartmentRepository;
import com.ahv.ms.departmentservice.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}

	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
		
		Department department = new Department(
				departmentDto.getId(),
				departmentDto.getDepartmentName(),
				departmentDto.getDepartmentDescription(),
				departmentDto.getDepartmentCode()
				);
		Department savedDepartment = departmentRepository.save(department);
		
		DepartmentDto savedDepartmentDto = new DepartmentDto(
				savedDepartment.getId(),
				savedDepartment.getDepartmentName(),
				savedDepartment.getDepartmentDescription(),
				savedDepartment.getDepartmentCode()
				);
		return savedDepartmentDto;
	}

	@Override
	public DepartmentDto getDepartmentByCode(String Departmentcode) {
		
		Department department = departmentRepository.findByDepartmentCode(Departmentcode);
		if(department==null) {
			throw new DepartmentNotFoundException("Department with code " + Departmentcode +" not found");
		}
		
		DepartmentDto departmentDto = new DepartmentDto(
				
				department.getId(),
				department.getDepartmentName(),
				department.getDepartmentDescription(),
				department.getDepartmentCode()
				);
		return departmentDto;
	}

}
