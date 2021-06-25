package com.project.academia.dao.implementation;

import com.project.academia.bean.Department;
import com.project.academia.bean.Employee;
import com.project.academia.dao.EmpDep;
import com.project.academia.dao.EmployeeDAO;
import com.project.academia.util.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public Employee emailVerify(Employee employee) {
        try (Session session = SessionUtil.getSession()){
            Query query = session.createQuery("from Employee where email=:email and department in (select dept_id from Department where name='HR')");
            query.setParameter("email", employee.getEmail());
//            query.setParameter("name", "HR");
            for (final Object fetch : query.list()) {
                return (Employee) fetch;
            }
        }
        catch (HibernateException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        return null;
    }

    @Override
    public List<EmpDep> getEmpList() {
        Session session = SessionUtil.getSession();
        List<Employee> employees = new ArrayList<>();
        List<EmpDep> empDepList = new ArrayList<>();
        try{
            for(final Object fetch : session.createQuery("from Employee ").list()){
                employees.add((Employee) fetch);
            }
        }
        catch (HibernateException e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        for(final Employee emp : employees){
            EmpDep ed = new EmpDep(emp,emp.getDepartment().getName());
            empDepList.add(ed);
        }

        return empDepList;
    }

    @Override
    public Employee registerEmployee(EmpDep empDep) {
        try(Session session = SessionUtil.getSession()) {
            Department department = new Department();
            Query query1 = session.createQuery("from Department where name=:name");
            query1.setParameter("name", empDep.getDep());
            for (final Object fetch1 : query1.list()) {
                department = (Department) fetch1;
            }
            Transaction transaction = session.beginTransaction();
            Employee employee = new Employee();
            employee.setDepartment(department);
            employee.setEmail(empDep.getEmp().getEmail());
            employee.setFirst_name(empDep.getEmp().getFirst_name());
            employee.setLast_name(empDep.getEmp().getLast_name());
            employee.setTitle(empDep.getEmp().getTitle());
            employee.setPhoto_path(empDep.getEmp().getPhoto_path());
            session.save(employee);
            transaction.commit();
            return employee;
        }
        catch (HibernateException e)
        {
            System.out.println(e.getLocalizedMessage());
            return null;
        }

    }

    @Override
    public boolean updateEmployee(EmpDep empDep) {
        Session session = SessionUtil.getSession();
        try{
            Transaction transaction = session.beginTransaction();
            Query query1 = session.createQuery("from Department where name=:name");
            query1.setParameter("name",empDep.getDep());
            Department department = new Department();
            for(final Object fetch1 : query1.list()){
                department = (Department) fetch1;
            }
            System.out.println(empDep.getEmp().getEmployee_id());
            Query query2 = session.createQuery("update Employee set first_name=:fname,last_name=:lname,email=:email,title=:title,photo_path=:photo_path,department=:department where employee_id=:employee_id");
            query2.setParameter("fname",empDep.getEmp().getFirst_name());
            query2.setParameter("lname",empDep.getEmp().getLast_name());
            query2.setParameter("email",empDep.getEmp().getEmail());
            query2.setParameter("title",empDep.getEmp().getTitle());
            query2.setParameter("photo_path",empDep.getEmp().getPhoto_path());
            query2.setParameter("department",department);
            query2.setParameter("employee_id",empDep.getEmp().getEmployee_id());
            query2.executeUpdate();
            transaction.commit();
            return true;
        }
        catch (HibernateException e)
        {
            System.out.println(e.getLocalizedMessage());
            return false;
        }

    }

    @Override
    public Employee allVerify(Employee employee) {
        try (Session session = SessionUtil.getSession()){
            Query query = session.createQuery("from Employee where email=:email");
            query.setParameter("email", employee.getEmail());
            for (final Object fetch : query.list()) {
                return (Employee) fetch;
            }
        }
        catch (HibernateException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        Session session = SessionUtil.getSession();
        try{
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("delete Employee where email=:email");
            query.setParameter("email",employee.getEmail());
            query.executeUpdate();
            transaction.commit();
            return true;
        }
        catch (HibernateException e){
            System.out.println(e.getLocalizedMessage());
            return false;
        }
    }
}
