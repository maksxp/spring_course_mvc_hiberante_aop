package com.mmm.spring.mvc_hibernate_aop.dao;

import com.mmm.spring.mvc_hibernate_aop.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();
//        //all in one statement
//        List <Employee> allEmployees = session
//                .createQuery("from Employee ", Employee.class)
//                .getResultList();

        //the same as above in two statements
        Query <Employee> query = session
                .createQuery("from Employee ", Employee.class);
        List <Employee> allEmployees = query
                .getResultList();
        return allEmployees;
    }
}
