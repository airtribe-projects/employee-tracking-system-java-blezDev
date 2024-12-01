package com.blez.employee_tracking_system.service;

import com.blez.employee_tracking_system.exception.ResourceNotFoundException;
import com.blez.employee_tracking_system.model.Employee;
import com.blez.employee_tracking_system.repository.EmployeeRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;

	public List<Employee> getAll() {
		return repository.findAll();
	}

	public Employee getById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
	}

	public Employee save(Employee employee) {
		return repository.save(employee);
	}

	public Employee update(Long id, Employee employee) {
		Employee existing = getById(id);
		existing.setFirstName(employee.getFirstName());
		existing.setLastName(employee.getLastName());
		existing.setEmail(employee.getEmail());
		return repository.save(existing);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public List<Employee> search(String firstName, String lastName, String email, Long departmentId) {
		return repository.findAll((root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();


			if (firstName != null) {
				predicates.add(cb.like(root.get("firstName"), "%" + firstName + "%"));
			}


			if (lastName != null) {
				predicates.add(cb.like(root.get("lastName"), "%" + lastName + "%"));
			}


			if (email != null) {
				predicates.add(cb.equal(root.get("email"), email));
			}


			if (departmentId != null) {
				predicates.add(cb.equal(root.get("department").get("id"), departmentId));
			}


			return cb.and(predicates.toArray(new Predicate[0]));
		});
	}

}

