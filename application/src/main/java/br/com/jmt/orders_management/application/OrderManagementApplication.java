package br.com.jmt.orders_management.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"br.com.jmt.orders_management.*"})
@SpringBootApplication(scanBasePackages = {"br.com.jmt.orders_management"})
@ComponentScan(basePackages = {"br.com.jmt.orders_management.*"})
@EntityScan(basePackages = {"br.com.jmt.orders_management.*"})
public class OrderManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderManagementApplication.class, args);
    }

}
