package com.blez.employee_tracking_system.service;


import com.blez.employee_tracking_system.exception.ResourceNotFoundException;
import com.blez.employee_tracking_system.model.Department;
import com.blez.employee_tracking_system.model.Project;
import com.blez.employee_tracking_system.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository repository;

	public List<Department> getAll() {
		return repository.findAll();
	}

	public Department getById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
	}

	public Department save(Department department) {
		return repository.save(department);
	}

	public Department update(Long id, Department department) {
		Department existing = getById(id);
		existing.setName(department.getName());
		return repository.save(existing);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public List<Project> getProjectsByDepartment(Long departmentId) {
		Department department = getById(departmentId);
		return department.getProjects();
	}
}
