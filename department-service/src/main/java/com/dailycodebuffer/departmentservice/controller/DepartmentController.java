package com.dailycodebuffer.departmentservice.controller;

import com.dailycodebuffer.departmentservice.client.EmployeeClient;
import com.dailycodebuffer.departmentservice.model.Department;
import com.dailycodebuffer.departmentservice.repository.DepartmentRepository;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeClient employeeClient; // it will start

    @PostMapping
    public Department addDept(@RequestBody Department department){
        LOGGER.info("Department add: {} ",department );
        return departmentRepository.addDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findByDeptId(@PathVariable("id") Long id){
        LOGGER.info("Department findByDeptId: id={} ",id );
        return departmentRepository.findById(id);
    }

    @GetMapping
    public List<Department> getAllDepartments(){
        LOGGER.info("Department getAllDepartments:  " );
        return departmentRepository.findAll();
    }

    @GetMapping("/with-employees")
    public List<Department> getAllDepartmentsWithEmployees(){
        LOGGER.info("Department getAllDepartments:  " );
        List<Department> departments =  departmentRepository.findAll();
        departments.forEach(department ->
                department.setEmployees(employeeClient.getEmployeesByDeptId(department.getId())));

        return departments;
    }
}
