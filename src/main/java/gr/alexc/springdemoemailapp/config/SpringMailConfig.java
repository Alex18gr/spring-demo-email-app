package gr.alexc.springdemoemailapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@RequiredArgsConstructor
public class SpringMailConfig {

    private final DatabaseTemplateResolver databaseTemplateResolver;

    @Bean
    public TemplateEngine emailTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        // Resolver for HTML mails with templates from the database
        templateEngine.addTemplateResolver(databaseTemplateResolver);
        return templateEngine;
    }

}
