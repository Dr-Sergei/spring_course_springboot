package com.sergei.spring.springboot.spring_course_springboot.dao;

import com.sergei.spring.springboot.spring_course_springboot.entity.Employee;
import jakarta.persistence.EntityManager;
//import org.hibernate.Session;
//import org.hibernate.query.Query;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees() {

//        Session session = entityManager.unwrap(Session.class);
//
//        //Query aus der Dependency Hibernate ("from Employee" means from class Employee not from SQL table )
//        Query<Employee> query = session.createQuery("from Employee", Employee.class);
//
//        //execute query and add it to Employee List
//        List<Employee> employeeList = query.getResultList();

        Query query = entityManager.createQuery("from Employee");
        List<Employee> employeeList = query.getResultList();

        return employeeList;
    }

    @Override
    public void saveNewEmployee(Employee employee) {
//        Session session = entityManager.unwrap(Session.class);
//
//        session.saveOrUpdate(employee);

        Employee newEmployee = entityManager.merge(employee);
        employee.setId(newEmployee.getId());
    }

    @Override
    public Employee getEmployee(int id) {
//        Session session = entityManager.unwrap(Session.class);
//
//        Employee employee = session.get(Employee.class, id);

        Employee employee = entityManager.find(Employee.class, id);

        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
//        Session session = entityManager.unwrap(Session.class);
//        Query<Employee> query = session.createQuery("delete from Employee " +
//                "where id =:employeeId");
//        query.setParameter("employeeId", id);
//        query.executeUpdate();

        Query query = entityManager.createQuery("delete from Employee " + "where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }


}
