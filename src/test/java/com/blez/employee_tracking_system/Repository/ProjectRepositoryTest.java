package com.blez.employee_tracking_system.Repository;


import com.blez.employee_tracking_system.model.Project;
import com.blez.employee_tracking_system.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectRepositoryTest {

	@Autowired
	private ProjectRepository repository;

	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@Test
	void testSaveAndFindAll() {
		Project project = new Project(null, null, 50000.0, "Project A", null);
		repository.save(project);

		List<Project> projects = repository.findAll();
		assertEquals(1, projects.size());
		assertEquals("Project A", projects.get(0).getName());
	}
}
