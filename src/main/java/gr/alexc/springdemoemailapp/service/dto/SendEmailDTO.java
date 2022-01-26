package gr.alexc.springdemoemailapp.service.dto;

import lombok.Data;

import java.util.Map;

@Data
public class SendEmailDTO {

    private String to;
    private String from;
    private String subject;
    private String template;
    private Map<String, Object> templateParams;

}
