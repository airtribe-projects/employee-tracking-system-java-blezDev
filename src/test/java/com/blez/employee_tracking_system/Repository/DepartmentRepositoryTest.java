package com.blez.employee_tracking_system.Repository;


import com.blez.employee_tracking_system.model.Department;
import com.blez.employee_tracking_system.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DepartmentRepositoryTest {

	@Autowired
	private DepartmentRepository repository;

	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@Test
	void testSaveAndFindAll() {
		Department department = new Department(null, null, 30000.0, "HR", null);
		repository.save(department);

		List<Department> departments = repository.findAll();
		assertEquals(1, departments.size());
		assertEquals("HR", departments.get(0).getName());
	}
}
