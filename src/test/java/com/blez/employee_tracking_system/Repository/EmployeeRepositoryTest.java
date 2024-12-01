package com.blez.employee_tracking_system.Repository;


import com.blez.employee_tracking_system.model.Employee;
import com.blez.employee_tracking_system.model.Role;
import com.blez.employee_tracking_system.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository repository;

	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@Test
	void testSaveAndFindAll() {
		Employee employee = new Employee(null, "Jane", "Doe", "jane.doe@example.com", Role.EMPLOYEE, null, null);
		repository.save(employee);

		List<Employee> employees = repository.findAll();
		assertEquals(1, employees.size());
		assertEquals("Jane", employees.get(0).getFirstName());
	}
}
