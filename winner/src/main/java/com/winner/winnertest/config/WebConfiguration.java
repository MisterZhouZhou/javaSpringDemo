package com.winner.winnertest.config;

import com.winner.winnertest.utils.date.USLocalDateFormatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDate;

public class WebConfiguration extends WebMvcConfigurerAdapter {
    @Override public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldType(LocalDate.class, new
                USLocalDateFormatter());
    }
}
