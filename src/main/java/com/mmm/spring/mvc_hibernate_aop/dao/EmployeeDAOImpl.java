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
//    @Transactional // this annotation used here if we have NO service layer
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

    @Override
    public void saveEmployee(Employee employee) {

        Session session = sessionFactory.getCurrentSession();

        //this method add new employee if id=0 and update exist employee in another case
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();

        Employee employee =session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {

        Session session = sessionFactory.getCurrentSession();

//        //option without Query
//        Employee employee =session.get(Employee.class, id);
//        session.delete(employee);

       //option with Query and setParameter
        Query <Employee> query = session
                .createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();

    }


}
