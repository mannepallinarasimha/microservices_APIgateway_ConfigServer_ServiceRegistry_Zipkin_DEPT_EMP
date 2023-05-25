package com.codebuffer.employeeservice.controller;

import com.codebuffer.employeeservice.model.Employee;
import com.codebuffer.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        LOGGER.info("Employee add: {} ",employee );
        return employeeRepository.addDepartment(employee);
    }


    @GetMapping("/{id}")
    public Employee findByEmployeeId(@PathVariable("id") Long id){
        LOGGER.info("Employee findByEmployeeId: id={} ",id );
        return employeeRepository.findById(id);
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        LOGGER.info("Employee getAllEmployees:  " );
        return employeeRepository.findAll();
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> getEmployeesByDeptId(@PathVariable("departmentId")  Long departmentId){
        LOGGER.info("Employee getEmployeesByDeptId:  {}", departmentId);
        return employeeRepository.findEmployeesByDeptId(departmentId);
    }
}
