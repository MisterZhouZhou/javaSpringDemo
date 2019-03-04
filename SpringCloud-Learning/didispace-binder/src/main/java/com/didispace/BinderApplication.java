package com.didispace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class BinderApplication {

    public static void main(String[] args){
        ApplicationContext context = SpringApplication.run(BinderApplication.class);

        Binder binder = Binder.get(context.getEnvironment());

        // 绑定简单配置
        FooProperties fooProperties = binder.bind("com.didispace", Bindable.of(FooProperties.class)).get();
        System.out.println("===="+fooProperties.getFoo());

        // 绑定List配置
        List<String> post = binder.bind("com.didispace.post", Bindable.listOf(String.class)).get();
        System.out.println("===="+post);

        List<PostInfo> posts = binder.bind("com.didispace.posts", Bindable.listOf(PostInfo.class)).get();
        System.out.println("===="+posts);

        // 读取配置
        System.out.println("===="+context.getEnvironment().containsProperty("com.didispace.database-platform"));
        System.out.println("===="+context.getEnvironment().containsProperty("com.didispace.databasePlatform"));

    }
}
