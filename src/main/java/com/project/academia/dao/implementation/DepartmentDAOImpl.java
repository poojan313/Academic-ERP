package com.project.academia.dao.implementation;

import com.project.academia.bean.Department;
import com.project.academia.bean.Employee;
import com.project.academia.dao.DepartmentDAO;
import com.project.academia.util.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {
    @Override
    public List<String> fetchDepartment() {
        Session session = SessionUtil.getSession();
        List<String> strings = new ArrayList<>();
        try {
            for(final Object fetch: session.createQuery("select name from Department ").list())
            {
                strings.add((String)fetch);
            }
        }
        catch (HibernateException e)
        {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        return strings;
    }

    @Override
    public boolean checkCount(Department department) {
        Session session = SessionUtil.getSession();
        try {
            long count1 = 0;
            Query query1 = session.createQuery("SELECT count(*) as counter from Employee group by department having department in (select dept_id from Department where name=:name)");
            query1.setParameter("name", department.getName());
            for (Object fetch1 : query1.list()) {
                count1 = (Long) fetch1;
            }
            long count2 = 0;
//        session.flush();
//        session.clear();
            Query query2 = session.createQuery("SELECT capacity from Department where name=:name2");
            query2.setParameter("name2", department.getName());
            for (Object fetch2 : query2.list()) {
                count2 = (Integer) fetch2;
            }
            if (count2 > count1) {
                return true;
            } else {
                return false;
            }
        }
        catch (HibernateException e){
            System.out.println(e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public String getName(Employee employee) {
        Session session = SessionUtil.getSession();
        Employee employee1 = new Employee();
        try{
            Query query = session.createQuery("from Employee where email=:email");
            query.setParameter("email",employee.getEmail());
            for(final Object fetch: query.list()){
                employee1 = (Employee) fetch;
            }
            return employee1.getDepartment().getName();
        }
        catch (HibernateException e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }

    }
}

