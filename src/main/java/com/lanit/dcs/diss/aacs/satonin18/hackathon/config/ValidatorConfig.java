package com.lanit.dcs.diss.aacs.satonin18.hackathon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
@ComponentScans( value = {
        @ComponentScan(
                basePackages = { "com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service"}
        )
})
public class ValidatorConfig {

    //	@Bean
//	public LocalValidatorFactoryBean validator() { return new LocalValidatorFactoryBean(); }
    @Bean
    public Validator validator() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        return vf.getValidator();
    }

}
