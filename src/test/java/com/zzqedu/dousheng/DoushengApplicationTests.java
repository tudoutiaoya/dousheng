package com.zzqedu.dousheng;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class DoushengApplicationTests {

    @Test
    void contextLoads() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toLocalDate());
        System.out.println(localDateTime.toLocalTime());
        LocalDateTime localDateTime1 = localDateTime.plusYears(1);
        LocalDate localDate1 = LocalDate.parse("20190910", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate parse = LocalDate.parse("2019-09-10", DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println(parse);
    }

}
