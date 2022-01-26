package gr.alexc.springdemoemailapp.repository;

import gr.alexc.springdemoemailapp.domain.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {

    Optional<EmailTemplate> findByTemplateName(String templateName);

}
