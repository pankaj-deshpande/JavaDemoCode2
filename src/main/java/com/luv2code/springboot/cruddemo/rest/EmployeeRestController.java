package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;

	// constructor injection
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		this.employeeService = theEmployeeService;
	}

	// return endpoint /employees to show all employees
	@GetMapping("/employees")
	public List<Employee> showAllEmployees() {
		return employeeService.findAll();
	}

	// return endpoint /employees/${employeeId} to show employee by id
	@GetMapping("/employees/{employeeId}")
	public Employee showEmployeeById(@PathVariable int employeeId) {
		Employee theEmployee = employeeService.findEmpByID(employeeId);

		if (theEmployee == null) {
			throw new RuntimeException("Employee Id not found: " + employeeId);
		}
		return theEmployee;
	}

	// return endpoint /employees to add new employee
	@PostMapping("/employees")
	public Employee saveEmployee(@RequestBody Employee tempEmployee) {
		tempEmployee.setId(0);
		employeeService.saveEmployee(tempEmployee);
		return tempEmployee;
	}

	// return endpoint /employees to update employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		employeeService.saveEmployee(theEmployee);

		return theEmployee;
	}

	// return endpoint /employees/{employeeId} to delete employee by id

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployeeById(@PathVariable int employeeId) {

		Employee theEmployee = employeeService.findEmpByID(employeeId);
		if (theEmployee == null) {
			throw new RuntimeException("Employee Id not found: " + employeeId);
		}

		employeeService.deleteEmpByID(employeeId);
		return "Deleted Employee Id: |" + employeeId + "|";
	}
}