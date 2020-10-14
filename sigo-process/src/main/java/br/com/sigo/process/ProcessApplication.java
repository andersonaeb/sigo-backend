package br.com.sigo.process;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class ProcessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessApplication.class, args);
    }
}
