package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> findAll();
	
	public Employee findEmpByID(int theId);
	
	public void deleteEmpByID(int theId);

	public void saveEmployee(Employee theEmployee);

}
