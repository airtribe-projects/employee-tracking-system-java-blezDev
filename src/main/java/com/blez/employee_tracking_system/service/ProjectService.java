package com.blez.employee_tracking_system.service;


import com.blez.employee_tracking_system.exception.ResourceNotFoundException;
import com.blez.employee_tracking_system.model.Project;
import com.blez.employee_tracking_system.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository repository;

	public List<Project> getAll() {
		return repository.findAll();
	}

	public Project getById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
	}

	public Project save(Project project) {
		return repository.save(project);
	}

	public Project update(Long id, Project project) {
		Project existing = getById(id);
		existing.setName(project.getName());
		existing.setEmployees(project.getEmployees());
		return repository.save(existing);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}

