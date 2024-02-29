package com.example.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
	
	private final EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping
	public List<Employee> getAllEmployees() {
		return employeeService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
		Employee employee = employeeService.findById(id);
		return ResponseEntity.ok(employee);
	}
	
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = employeeService.save(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
		if (employeeService.findById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		employee.setId(id);
		employeeService.save(employee);
		return ResponseEntity.ok("Employee updated successfully");
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
		Employee employee = employeeService.findById(id);
		employeeService.delete(employee);
		return ResponseEntity.ok("Employee deleted successfully");
	}
	
}