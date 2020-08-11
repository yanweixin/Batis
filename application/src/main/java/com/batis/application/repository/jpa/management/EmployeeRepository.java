package com.batis.application.repository.jpa.management;

import com.batis.application.entity.management.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
