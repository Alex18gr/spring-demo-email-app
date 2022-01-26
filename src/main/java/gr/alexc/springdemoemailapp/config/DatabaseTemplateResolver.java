package gr.alexc.springdemoemailapp.config;

import gr.alexc.springdemoemailapp.domain.EmailTemplate;
import gr.alexc.springdemoemailapp.repository.EmailTemplateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.thymeleaf.templateresource.ITemplateResource;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * Database template resolver to get templates from the databased based on the unique provided template name
 */
@Component
public class DatabaseTemplateResolver extends StringTemplateResolver {

    private final Logger logger = LoggerFactory.getLogger(DatabaseTemplateResolver.class);

    private final EmailTemplateRepository templateRepository;

    public DatabaseTemplateResolver(EmailTemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
        this.setResolvablePatterns(Collections.singleton("mail-*"));
        this.setTemplateMode(TemplateMode.HTML);
        this.setCacheable(false);
    }

    @Override
    protected ITemplateResource computeTemplateResource(
            IEngineConfiguration configuration,
            String ownerTemplate,
            String templateName,
            Map<String, Object> templateResolutionAttributes
    ) {
        logger.info("Loading template {} from DB", templateName);
        Optional<EmailTemplate> emailTemplateOptional = templateRepository.findByTemplateName(templateName);
        if (emailTemplateOptional.isEmpty()) {
            return null;
        }
        return super.computeTemplateResource(configuration, ownerTemplate, emailTemplateOptional.get().getContent(), templateResolutionAttributes);
    }
}
