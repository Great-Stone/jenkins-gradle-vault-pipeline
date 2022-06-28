package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;

@Configuration
@ConditionalOnProperty(name="spring.cloud.vault.aws.enabled")
@RefreshScope
@RestController
@SpringBootApplication
@EnableScheduling
public class AWSConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(VaultAWSConfiguration.class);

	@Autowired
	AwsConfigurationProperties awsConfigurationProperties;

	public static void main(String[] args) {
		SpringApplication.run(AWSConfiguration.class, args);
	}

	@RefreshScope
	@Scheduled(fixedRate=2000)
	public void basicAWSCredentials() throws IOException {
		logger.info(awsConfigurationProperties.toString());
	}

	@RefreshScope
	@RequestMapping(method = RequestMethod.GET, path = "/")
	public String webAWSCredentials() throws IOException {
		return "<h1>AWS</h1>"
		.concat("<h2>" + awsConfigurationProperties.toString() + "</h2>");
	}
}