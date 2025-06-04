package com.greenacademy.api;

import com.greenacademy.entity.Customer;
import com.greenacademy.entity.KhachHang;
import com.greenacademy.service.CustomerService;
import com.greenacademy.service.Impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerApi {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public Customer saveCustomer(@RequestBody Customer khachHang) {
        return customerService.saveCustomer(khachHang);
    }
}
