package com.somu.controller;

import com.somu.model.Customer;
import com.somu.service.CustomerService;
import com.somu.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    @Qualifier("jdbc")
    private CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<?> getCustomer()
    {
        return new ResponseEntity<>(new CustomResponse(customerService.getAllCustomer()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Integer id){
        return customerService.getCustomerById(id).isPresent() ? new ResponseEntity<>(new CustomResponse(customerService.getCustomerById(id).get()), HttpStatus.OK) : new ResponseEntity<>(new CustomResponse(new Customer()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/provision", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer)
    {
        Customer response = customerService.createCustomer(customer);
        if(response == null)
        {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(response), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Integer id){
        if(customerService.deleteCustomer(id)){
            return new ResponseEntity<>(new CustomResponse("Customer Deleted Successfully"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new CustomResponse("Customer not with provided ID"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Integer id, @RequestBody Customer customer){
        Customer cust = customerService.updateCustomer(id, customer);
        if(cust == null)
        {
            return new ResponseEntity<>(new CustomResponse("Customer is not present with ID : "+id), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomResponse(cust), HttpStatus.OK);
    }

    @GetMapping("/cpu-utilization")
    public String getCpuUtilization() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        double cpuUtilization = osBean.getSystemLoadAverage();
        return "CPU Utilization: " + (cpuUtilization * 100) + "%";
    }
}
