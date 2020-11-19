package com.springaop.example.controller;

import com.springaop.example.aspect.EmailAllowed;
import com.springaop.example.model.EmailDto;
import com.springaop.example.model.SendEmailRequest;
import com.springaop.example.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emails")
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    @EmailAllowed
    public void send(@RequestBody SendEmailRequest sendEmailRequest) {
        emailService.send(sendEmailRequest);
    }

    @GetMapping
    public List<EmailDto> getAll() throws Exception {
        return emailService.getAll();
    }
}
