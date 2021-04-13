package com.java.springboot.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtil {
    public String formatLocalDateTimeToDatabaseStyle(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm:ss").format(localDateTime);
    }
}

/**
 * @Component é uma classe que vai ser escaneada pela @ComponentScan
 */
