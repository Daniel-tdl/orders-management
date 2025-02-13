package br.com.jmt.orders_management.application.config;

import br.com.jmt.orders_management.domain.util.QueueConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(QueueConstant.CREATE_ORDER_EXCHANGE);
    }

    @Bean
    public FanoutExchange fanoutExchangeDLQ() {
        return new FanoutExchange(QueueConstant.CREATE_ORDER_EXCHANGE_DLQ);
    }

    @Bean
    public Queue queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", QueueConstant.CREATE_ORDER_EXCHANGE_DLQ);

        return new Queue(QueueConstant.CREATE_ORDER, true, false, false, args);
    }

    @Bean
    public Binding binding() {
        Queue queue = queue();
        FanoutExchange exchange = new FanoutExchange(QueueConstant.CREATE_ORDER_EXCHANGE);
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Queue queueDLQ() {
        return new Queue(QueueConstant.CREATE_ORDER_DLQ);
    }

    @Bean
    public Binding bindingDLQ() {
        Queue queue = queueDLQ();
        FanoutExchange exchange = new FanoutExchange(QueueConstant.CREATE_ORDER_EXCHANGE_DLQ);
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(
            RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(ObjectMapper objectMapper) {
        objectMapper.findAndRegisterModules();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}

