package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tw.apistackbase.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeResource {
	private List<Employee> employees = new ArrayList<Employee>();

	@GetMapping(path = "/")
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> getEmployees(){
//		employees.add(new Employee("001","yang",20,"Female"));
//		employees.add(new Employee("002","lihua",20,"Male"));
		return employees;
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Employee> getOneEmployee(@PathVariable String id) {
		for(Employee employee : employees) {
			if(employee.getId().equals(id)) {
				return ResponseEntity.ok(employee);
			}
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@PostMapping(path = "/")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@RequestBody Employee employee){
		employees.add(employee);
		return employee;
	}

	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Employee> updatEmployee(@PathVariable String id, @RequestBody Employee employee){
		for(Employee updateEmployee : employees) {
			if(updateEmployee.getId().equals(id)) {		
				updateEmployee.setId(employee.getId());
				updateEmployee.setName(employee.getName()); 
				updateEmployee.setAge(employee.getAge()); 
				updateEmployee.setGender(employee.getName()); 		
				return ResponseEntity.ok(updateEmployee);
			}
			
		}

		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Employee> deleteOneEmployee(@PathVariable String id) {
		for(Employee employee : employees) {
			if(employee.getId().equals(id)) {
				employees.remove(employee);
				return ResponseEntity.ok(null);
			}
		}
		return ResponseEntity.notFound().build();
	}

}
