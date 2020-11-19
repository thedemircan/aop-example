package com.springaop.example.service;

import com.springaop.example.aspect.LogExecutionTime;
import com.springaop.example.model.EmailDto;
import com.springaop.example.model.SendEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    public void send(SendEmailRequest sendEmailRequest) {
        //Let's think we are sending the email client
        return;
    }

    @LogExecutionTime
    public List<EmailDto> getAll() throws Exception {
        Thread.sleep(3000); //wait 3 seconds
        final EmailDto emailDto1 = EmailDto.builder()
                .id(1L)
                .to("mehmet@demircan.com")
                .from("it@mnd.com")
                .content("Email 1")
                .build();
        final EmailDto emailDto2 = EmailDto.builder()
                .id(2L)
                .to("all@demircan.com")
                .from("ik@mnd.com")
                .content("Email 2")
                .build();
        return List.of(emailDto1, emailDto2);
    }
}
