package com.example.blogclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.example.blog")
public class ApiProperties {

	private String url; 
	private String publicurl;
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPublicurl() {
		return publicurl;
	}
	
	public void setPublicurl(String publicurl) {
		this.publicurl = publicurl;
	}
	
}