package com.blez.employee_tracking_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String email;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role; // e.g., ADMIN, MANAGER, EMPLOYEE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = true) // Add nullable=true if no department is required
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

    public Collection<String> getRoles() {
        return role != null ? List.of(role.name()) : List.of();
    }

    public String getName() {
        return null;
    }

}
