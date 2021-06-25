package com.project.academia.dao;

import com.project.academia.bean.Employee;

import java.util.List;

public class EmpDep {
    private Employee emp;
    private String dep;
    public EmpDep(){

    }

    public EmpDep(Employee emp, String dep) {
        this.emp = emp;
        this.dep = dep;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }
}
