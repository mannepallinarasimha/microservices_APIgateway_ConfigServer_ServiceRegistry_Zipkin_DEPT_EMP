package com.codebuffer.employeeservice.repository;

import com.codebuffer.employeeservice.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    private List<Employee> employeeList = new ArrayList<>();
    public Employee addDepartment(Employee employee){
        employeeList.add(employee);
        return employee;
    }

    public Employee findById(Long id){
        return employeeList
                .stream()
                .filter(x -> x.id().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Employee> findAll(){
        return employeeList;
    }

    //method to find all the employees based on deptId
    public List<Employee> findEmployeesByDeptId(Long departmentId){
        return employeeList.stream().filter(x -> x.departmentId().equals(departmentId)).toList();
    }
}
