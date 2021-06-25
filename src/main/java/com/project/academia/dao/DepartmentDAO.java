package com.project.academia.dao;

import com.project.academia.bean.Department;
import com.project.academia.bean.Employee;

import java.util.List;

public interface DepartmentDAO {
    List<String> fetchDepartment();
    boolean checkCount(Department department);
    String getName(Employee employee);
}
