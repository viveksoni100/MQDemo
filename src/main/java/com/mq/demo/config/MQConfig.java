package com.mq.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author viveksoni100
 */
@Configuration
public class MQConfig {

    public static final String QUEUE = "vaccine_queue";
    public static final String EXCHANGE = "vaccine_exchange";

    // creating queue
    @Bean
    public Queue vaccineQueue() {
        return new Queue(QUEUE);
    }

    // creating exchange of type topic
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    // binding our queue with out exchange (method of message transfer)
    @Bean
    public Binding binding(TopicExchange exchange) {
        return BindingBuilder.bind(vaccineQueue()).to(exchange).with(vaccineQueue().getName());
    }

    // utility for doing essential conversions
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    // making the connection with RabbitMQ server
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
