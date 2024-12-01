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
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Double budget;

    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id") // Ensure consistent column naming
    private Department department;
    public Project(Long id, List<Employee> employees, Double budget, String name, Department department) {
        this.id = id;
        this.employees = employees;
        this.budget = budget;
        this.name = name;
        this.department = department;
    }


}
