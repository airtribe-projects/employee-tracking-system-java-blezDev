package com.blez.employee_tracking_system.repository;

import com.blez.employee_tracking_system.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {}
