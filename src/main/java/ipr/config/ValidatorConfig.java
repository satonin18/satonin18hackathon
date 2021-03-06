package ipr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
@ComponentScans( value = {
        @ComponentScan(
                basePackages = { "ipr.web.service"}
        )
})
public class ValidatorConfig {

    @Bean
	public LocalValidatorFactoryBean validator() { return new LocalValidatorFactoryBean(); }

}
