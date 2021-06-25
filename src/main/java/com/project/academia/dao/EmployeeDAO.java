package com.project.academia.dao;

import com.project.academia.bean.Employee;

import javax.json.bind.annotation.JsonbTransient;
import java.util.List;

public interface EmployeeDAO {
    Employee emailVerify(Employee employee);
    List<EmpDep> getEmpList();
    Employee registerEmployee(EmpDep empDep);
    boolean updateEmployee(EmpDep empDep);
    Employee allVerify(Employee employee);
    boolean deleteEmployee(Employee employee);
}
