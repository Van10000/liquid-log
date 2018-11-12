package ru.naumen.perfhouse;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import ru.naumen.ApplicationContextProvider.ApplicationContextProvider;

@SpringBootApplication(scanBasePackages = { "ru.naumen" })
public class PerfhouseApplication extends SpringBootServletInitializer
{
    @Autowired
    public PerfhouseApplication(ApplicationContextProvider contextProvider) {
        // Initialize static context field by injecting it
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PerfhouseApplication.class);
    }

    public static void main(String[] args) throws IOException, ParseException
    {
        SpringApplication.run(PerfhouseApplication.class, args);
    }

}
