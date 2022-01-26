package gr.alexc.springdemoemailapp.service;

import gr.alexc.springdemoemailapp.service.dto.SendEmailDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final Logger logger = LoggerFactory.getLogger(EmailSenderService.class);

    private final JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;

    /**
     * Create and send a mime email based on the provided email data
     * @param emailDTO email data provided for the email
     */
    public void sendEmail(SendEmailDTO emailDTO) {

        logger.info("Sending email from \"{}\" to \"{}\" using template \"{}\"", emailDTO.getFrom(), emailDTO.getTo(), emailDTO.getTemplate());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            helper.setFrom(emailDTO.getFrom());
            helper.setTo(emailDTO.getTo());
            helper.setSubject(emailDTO.getSubject());

            helper.setText(createHtml(emailDTO.getTemplate(), emailDTO.getTemplateParams()), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    /**
     * Create the HTML content based on the template id provided
     * @param templateName the name of the template, it is unique in the databse
     * @param params the parameters to configure the template content
     * @return the final parsed HTML email content
     */
    private String createHtml(String templateName, Map<String, Object> params) {
        final Context context = new Context();
        context.setVariables(params);
        return templateEngine.process(templateName, context);
    }


}
