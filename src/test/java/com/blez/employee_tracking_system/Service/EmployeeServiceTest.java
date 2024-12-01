package com.blez.employee_tracking_system.Service;


import com.blez.employee_tracking_system.exception.ResourceNotFoundException;
import com.blez.employee_tracking_system.model.Employee;
import com.blez.employee_tracking_system.model.Role;
import com.blez.employee_tracking_system.repository.EmployeeRepository;
import com.blez.employee_tracking_system.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	@Mock
	private EmployeeRepository repository;

	@InjectMocks
	private EmployeeService service;

	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@Test
	void testGetByIdSuccess() {
		Employee employee = new Employee(1L, "John", "Doe", "john.doe@example.com", Role.EMPLOYEE, null, null);
		when(repository.findById(1L)).thenReturn(Optional.of(employee));

		Employee result = service.getById(1L);

		assertEquals("John", result.getFirstName());
		verify(repository, times(1)).findById(1L);
	}

	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@Test
	void testGetByIdNotFound() {
		when(repository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> service.getById(1L));
		verify(repository, times(1)).findById(1L);
	}

	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@Test
	void testGetAll() {
		List<Employee> employees = Arrays.asList(
				new Employee(1L, "John", "Doe", "john.doe@example.com", Role.EMPLOYEE, null, null),
				new Employee(2L, "Jane", "Doe", "jane.doe@example.com", Role.EMPLOYEE, null, null)
		);

		when(repository.findAll()).thenReturn(employees);

		List<Employee> result = service.getAll();
		assertEquals(2, result.size());
		assertEquals("John", result.get(0).getFirstName());
		verify(repository, times(1)).findAll();
	}

	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@Test
	void testSave() {
		Employee employee = new Employee(1L, "John", "Doe", "john.doe@example.com", Role.EMPLOYEE, null, null);
		Employee savedEmployee = new Employee(2L, "Jane", "Doe", "jane.doe@example.com", Role.EMPLOYEE, null, null);

		when(repository.save(employee)).thenReturn(savedEmployee);

		Employee result = service.save(employee);
		assertNotNull(result);
		assertEquals(2L, result.getId());
		assertEquals("Jane", result.getFirstName());
		verify(repository, times(1)).save(employee);
	}

	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@Test
	void testUpdate() {
		Employee employee = new Employee(1L, "John", "Doe", "john.doe@example.com", Role.EMPLOYEE, null, null);
		Employee updatedEmployee = new Employee(2L, "Jane", "Doe", "jane.doe@example.com", Role.EMPLOYEE, null, null);

		when(repository.findById(1L)).thenReturn(Optional.of(employee));
		when(repository.save(employee)).thenReturn(updatedEmployee);

		Employee result = service.update(1L, new Employee(1L, "John", "Doe", "john.doe@example.com", Role.EMPLOYEE, null, null));
		assertNotNull(result);
		assertEquals("Jane", result.getFirstName());
		verify(repository, times(1)).findById(1L);
		verify(repository, times(1)).save(employee);
	}

	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@Test
	void testDelete() {
		doNothing().when(repository).deleteById(1L);

		service.delete(1L);

		verify(repository, times(1)).deleteById(1L);
	}

	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@Test
	void testSearchWithoutPaths() {
		// Mock the repository behavior
		when(repository.findAll(any(Specification.class))).thenAnswer((Answer<List<Employee>>) invocation -> {
			// Simulating the search result based on the input
			List<Employee> employees = Arrays.asList(
					new Employee(1L, "John", "Doe", "john.doe@example.com", Role.EMPLOYEE, null, null),
					new Employee(2L, "John", "Doe", "john.doe@example.com", Role.EMPLOYEE, null, null)
			);
			// You can mock your filtering logic here if necessary
			return employees;
		});

		// Call the search method
		List<Employee> result = service.search("John", "Doe", "john.doe@example.com", 1L);

		// Verify the results
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("John", result.get(0).getFirstName());
		assertEquals("Doe", result.get(0).getLastName());
		assertEquals("john.doe@example.com", result.get(0).getEmail());

		// Ensure the repository method was called
		verify(repository, times(1)).findAll(any(Specification.class));
	}


}
