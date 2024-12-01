package com.blez.employee_tracking_system.Controller;


import com.blez.employee_tracking_system.controller.ProjectController;
import com.blez.employee_tracking_system.model.Project;
import com.blez.employee_tracking_system.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private ProjectService service;

	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@Test
	void testGetAllProjects() throws Exception {
		Project project = new Project(1L, null, 10000.0, "Project A", null);
		Mockito.when(service.getAll()).thenReturn(List.of(project));

		mockMvc.perform(get("/projects")
						.with(oauth2Login().authorities(() -> "ROLE_ADMIN")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].name").value("Project A"));
	}

	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@Test
	void testAddProject() throws Exception {
		Project project = new Project(1L, null, 20000.0, "Project B", null);
		Mockito.when(service.save(any(Project.class))).thenReturn(project);

		String jsonPayload = """
				{
				  "name": "Project B",
				  "budget": 20000
				}
				""";

		mockMvc.perform(post("/projects")
						.with(oauth2Login().authorities(() -> "ROLE_ADMIN"))
						.with(csrf())
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonPayload))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Project B"));
	}

	@Test
	@WithMockUser(roles = {"ADMIN", "MANAGER"})
	void testGetProjectById() throws Exception {
		Project project = new Project(1L, null, 20000.0, "Project B", null);
		Mockito.when(service.getById(1L)).thenReturn(project);

		mockMvc.perform(get("/projects/1")
						.with(oauth2Login().authorities(() -> "ROLE_ADMIN"))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Project B"));
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void testUpdateProject() throws Exception {
		Project project = new Project(1L, null, 20000.0, "Project B", null);
		Mockito.when(service.update(eq(1L), any(Project.class))).thenReturn(project);

		mockMvc.perform(put("/projects/1")
						.with(oauth2Login().authorities(() -> "ROLE_ADMIN"))
						.with(csrf())
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"Updated Project Alpha\",\"description\":\"Updated description for Project Alpha\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Project B"));
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void testDeleteProject() throws Exception {
		Mockito.doNothing().when(service).delete(1L);

		mockMvc.perform(delete("/projects/1")
						.with(oauth2Login().authorities(() -> "ROLE_ADMIN"))
						.with(csrf())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
