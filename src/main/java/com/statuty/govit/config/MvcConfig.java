package com.statuty.govit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by statut on 18/05/17.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/activation").setViewName("activation");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/logout").setViewName("logout");
        registry.addViewController("/login").setViewName("login");
    }

}