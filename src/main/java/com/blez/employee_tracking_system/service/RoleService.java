package com.blez.employee_tracking_system.service;

import com.blez.employee_tracking_system.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

	private final EmployeeRepository employeeRepository;

	public List<String> getRolesByEmail(String email) {
		return employeeRepository.findByEmail(email)
				.map(employee -> List.of(employee.getRole().name())) // Assume a single role, adjust if multiple
				.orElseThrow(() -> new RuntimeException("User not found with email: " + email));
	}
}

