package com.ahv.ms.departmentservice.service;

import com.ahv.ms.departmentservice.dto.DepartmentDto;

public interface DepartmentService {

	DepartmentDto saveDepartment(DepartmentDto departmentDto);
	DepartmentDto getDepartmentByCode(String code);
}
