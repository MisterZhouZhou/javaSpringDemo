package com.zw.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Describe: 定制错误页面
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){

        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
                ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");

                container.addErrorPages(error401Page,error404Page,error403Page,error500Page);
            }
        };
    }

    /**
     * 添加图片资源访问
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String imagesPath = WebMvcConfig.class.getClassLoader().getResource("").getPath();
        if(imagesPath.indexOf(".jar")>0){
            imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
        }else if(imagesPath.indexOf("/target/classes")>0){
            // 指向工程目录下emailImg目录
            imagesPath = "file:"+imagesPath.substring(0, imagesPath.indexOf("target"));
            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/"))+"/emailImg/";
        }
        registry.addResourceHandler("/image/**").addResourceLocations(imagesPath);
        super.addResourceHandlers(registry);
    }
}
