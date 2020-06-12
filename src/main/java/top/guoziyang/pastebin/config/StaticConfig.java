package top.guoziyang.pastebin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.*;

@Configuration
public class StaticConfig {

    @Bean
    public SimpleUrlHandlerMapping customFaviconHandlerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Integer.MIN_VALUE);
        Map map = new HashMap();
        map.put("/favicon.ico", faviconRequestHandler());
        map.put("/github.css", cssRequestHandler());
        mapping.setUrlMap(map);
        return mapping;
    }

    @Bean
    protected ResourceHttpRequestHandler faviconRequestHandler() {
        ResourceHttpRequestHandler requestHandler
                = new ResourceHttpRequestHandler();
        ClassPathResource classPathResource
                = new ClassPathResource("favicon.ico");
        List<Resource> locations = Arrays.asList(classPathResource);
        requestHandler.setLocations(locations);
        return requestHandler;
    }

    @Bean
    protected ResourceHttpRequestHandler cssRequestHandler() {
        ResourceHttpRequestHandler requestHandler
                = new ResourceHttpRequestHandler();
        ClassPathResource classPathResource
                = new ClassPathResource("static/github.css");
        List<Resource> locations = Arrays.asList(classPathResource);
        requestHandler.setLocations(locations);
        return requestHandler;
    }

}
