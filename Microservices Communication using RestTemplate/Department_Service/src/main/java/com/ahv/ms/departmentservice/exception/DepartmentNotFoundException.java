package com.ahv.ms.departmentservice.exception;

public class DepartmentNotFoundException extends RuntimeException{

	public DepartmentNotFoundException(String message) {
		super(message);
	}
}
