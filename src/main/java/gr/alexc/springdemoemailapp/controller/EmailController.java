package gr.alexc.springdemoemailapp.controller;

import gr.alexc.springdemoemailapp.service.EmailSenderService;
import gr.alexc.springdemoemailapp.service.dto.SendEmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailSenderService emailSenderService;

    @PostMapping("send-mail")
    public void sendMimeMail(@RequestBody SendEmailDTO emailDTO) {
        emailSenderService.sendEmail(emailDTO);
    }

}
