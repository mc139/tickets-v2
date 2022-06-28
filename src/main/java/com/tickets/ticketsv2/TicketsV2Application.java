package com.tickets.ticketsv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class TicketsV2Application {

    public static void main(String[] args) {
        SpringApplication.run(TicketsV2Application.class, args);
    }

    // TODO: 27.06.2022 Internationalization i18n in exceptions

//    @Bean
//    public ReloadableResourceBundleMessageSource messageSource() {
//        ReloadableResourceBundleMessageSource bundleMessageSource = new ReloadableResourceBundleMessageSource();
//        bundleMessageSource.setBasename("classpath:i18n/messages");
//        bundleMessageSource.setDefaultEncoding("UTF-8");
//        return bundleMessageSource;
//    }
}
