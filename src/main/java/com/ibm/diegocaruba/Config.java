package com.ibm.diegocaruba;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//@Configuration
//@Profile("development")
@Development
public class Config {

    @Bean(name = "applicationName")
    public String applicationName() {
        return "Sistema de Vendas";
    }

    @Bean
    public CommandLineRunner executar() {
        return args -> {
            System.out.println("Rodando desenvolvimento");
        };
    }
}
