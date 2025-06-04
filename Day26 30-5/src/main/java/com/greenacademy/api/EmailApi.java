package com.greenacademy.api;


import com.greenacademy.model.EmailRequest;
import com.greenacademy.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/email")
public class EmailApi {
    @Autowired
    EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest request) {
        emailService.sendSimpleEmail(
                request.getTo(),
                request.getSubject(),
                request.getBody()
        );
        return "Email sent successfully";
    }

    @PostMapping(value = "/send-attachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> sendAttachment(@RequestPart("to") String to,
                                            @RequestPart("subject") String subject,
                                            @RequestPart("body") String body,
                                            @RequestPart("file") MultipartFile file
                                            ) {
        emailService.sendEmail(to, subject, body, file);
        return ResponseEntity.ok("Email sent successfully");
    }
}
