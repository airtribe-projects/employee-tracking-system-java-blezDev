package com.blez.employee_tracking_system.controller;


import com.blez.employee_tracking_system.model.Department;
import com.blez.employee_tracking_system.model.Project;
import com.blez.employee_tracking_system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	@Autowired
	private DepartmentService service;

	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	@GetMapping
	public List<Department> getAllDepartments() {
		return service.getAll();
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	@GetMapping("/{id}")
	public Department getDepartmentById(@PathVariable Long id) {
		return service.getById(id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public Department addDepartment(@RequestBody Department department) {
		return service.save(department);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public Department updateDepartment(@PathVariable Long id, @RequestBody Department department) {
		return service.update(id, department);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void deleteDepartment(@PathVariable Long id) {
		service.delete(id);
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	@GetMapping("/{id}/projects")
	public List<Project> getProjectsByDepartment(@PathVariable Long id) {
		return service.getProjectsByDepartment(id);
	}
}


