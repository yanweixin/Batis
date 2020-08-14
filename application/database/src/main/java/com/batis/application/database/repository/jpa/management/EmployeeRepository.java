package com.batis.application.database.repository.jpa.management;

import com.batis.application.database.entity.management.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
