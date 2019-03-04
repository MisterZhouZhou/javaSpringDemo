package com.didispace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class ListenerApplication {

    public static void main(String[] args){
        SpringApplication.run(ListenerApplication.class, args);
    }

    @Bean
    public DataLoader dataLoader(){
        return new DataLoader();
    }

    @Slf4j
    static class DataLoader implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception{
            System.out.println("DataLoader=====");
//            log.info("Loading data...");
        }
    }
}
