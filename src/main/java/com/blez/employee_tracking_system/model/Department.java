package com.blez.employee_tracking_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String name;

    @NonNull
    private Double budget;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    @OneToMany(mappedBy = "department")
    private List<Project> projects;

    public Department(Long id, List<Employee> employees, Double budget, String name, List<Project> projects) {
        this.id = id;
        this.employees = employees;
        this.budget = budget;
        this.name = name;
        this.projects = projects;
    }
}
