package com.sh.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.sh.config.BeetlProperties;

@ComponentScan(basePackages = {"com.common.controller", "com.sh.controller", "com.sh.application", "com.sh.service.impl"})
@EnableConfigurationProperties({BeetlProperties.class})
@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration(exclude = {ThymeleafAutoConfiguration.class})
@ServletComponentScan
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate(clientHttpRequestFactory());
	}

	@Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory();
    }

}
