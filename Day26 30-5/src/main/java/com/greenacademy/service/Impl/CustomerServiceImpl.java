package com.greenacademy.service.Impl;


import com.greenacademy.entity.Customer;
import com.greenacademy.repository.CustomerRepository;
import com.greenacademy.service.CustomerService;
import com.greenacademy.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmailService emailService;


    @Override
    public Customer saveCustomer(Customer khachHang) {
        khachHang.setId(UUID.randomUUID().toString());
//        return  customerRepository.save(khachHang);

        Customer savedCustomer = customerRepository.save(khachHang);

        // Gửi mail sau khi lưu thành công
        if (savedCustomer.getEmail() != null && !savedCustomer.getEmail().isEmpty()) {
            String subject = "Thông báo đặt hàng thành công!";
            String body = "Xin chào " + savedCustomer.getName() + ",\n\nCảm ơn bạn đặt hàng, đơn hàng của bạn đã thành công. Chúng tôi rất vui được phục vụ bạn!";
            emailService.sendSimpleEmail(savedCustomer.getEmail(), subject, body);
        }

        return savedCustomer;
    }
}
