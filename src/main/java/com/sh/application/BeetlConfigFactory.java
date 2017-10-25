package com.sh.application;

import java.io.IOException;

import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import com.sh.config.BeetlProperties;

@Configuration
public class BeetlConfigFactory {

	@Autowired
	private BeetlProperties beetlProperties;

	@Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
    	BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
		try {
			// WebAppResourceLoader 配置root路径是关键
			WebAppResourceLoader webAppResourceLoader = new WebAppResourceLoader(patternResolver.getResource(beetlProperties.getRoot()).getFile().getPath());
			beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //读取配置文件信息
        return beetlGroupUtilConfiguration;
    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setPrefix("/");
        beetlSpringViewResolver.setSuffix(beetlProperties.getSuffix());
        beetlSpringViewResolver.setContentType(beetlProperties.getContentType());
        beetlSpringViewResolver.setOrder(beetlProperties.getOrder());
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;
    }

}
