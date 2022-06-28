package com.example.demo;

import static com.example.demo.AwsConfigurationProperties.PREFIX;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(PREFIX)
@Component
public class AwsConfigurationProperties {
  public static final String PREFIX = "cloud.aws.credentials";
	
	private String accessKey;

	private String secretKey;

  private String sessionToken;

	public String getAccesskey() {
		return accessKey;
	}

	public void setAccesskey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	@Override
	public String toString() {
		return "AwsConfigurationProperties [accessKey=" + accessKey + ", secretKey=" + secretKey + ", sessionToken=tokentoken]";
	}	
}