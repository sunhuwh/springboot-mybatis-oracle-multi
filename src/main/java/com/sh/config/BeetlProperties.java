package com.sh.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="beetl",locations = "classpath:beetl.properties")
public class BeetlProperties {

    private String root;

    private String prefix;

    private String suffix;

    private int order;

    private String contentType;

    private String cofig;

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getCofig() {
		return cofig;
	}

	public void setCofig(String cofig) {
		this.cofig = cofig;
	}


}
