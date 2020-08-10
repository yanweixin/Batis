package com.batis.application.repository.management;

import com.batis.application.entity.management.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
