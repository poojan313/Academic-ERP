package com.project.academia.services;

import com.project.academia.bean.Employee;
import com.project.academia.dao.EmpDep;
import com.project.academia.dao.EmployeeDAO;
import com.project.academia.dao.implementation.EmployeeDAOImpl;

import java.util.List;

public class EmployeeService {
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    public Employee verifyEmail(Employee employee){
        return employeeDAO.emailVerify(employee);
    }
    public List<EmpDep> getEmployees()
    {
        return  employeeDAO.getEmpList();
    }
    public Employee registerEmployee(EmpDep empDep){ return employeeDAO.registerEmployee(empDep); }
    public boolean employeeUpdate(EmpDep empDep){ return employeeDAO.updateEmployee(empDep); }
    public Employee verifyAll(Employee employee){ return employeeDAO.allVerify(employee); }
    public boolean employeeDelete(Employee employee){ return employeeDAO.deleteEmployee(employee); }
}
