package com.greenacademy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
    void sendSimpleEmail(String to, String subject, String body);
    void sendEmail(String to, String subject, String body, MultipartFile file);
}

    

