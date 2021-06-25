package com.project.academia.services;

import com.project.academia.bean.Department;
import com.project.academia.bean.Employee;
import com.project.academia.dao.DepartmentDAO;
import com.project.academia.dao.implementation.DepartmentDAOImpl;

import java.util.List;

public class DepartmentService {
    DepartmentDAO departmentDAO = new DepartmentDAOImpl();
    public List<String> getDept(){
        return departmentDAO.fetchDepartment();
    }
    public boolean departmentCount(Department department){
        return departmentDAO.checkCount(department);
    }
    public String getName(Employee employee){ return departmentDAO.getName(employee); }
}
