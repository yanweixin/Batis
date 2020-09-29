package com.batis.application.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AmpqConfig {
    static final String prefix = "spring-boot.";

    @Bean
    Queue defaultQueue() {
        return new Queue(prefix + "default", false);
    }

    @Bean
    Queue notifyQueue() {
        return new Queue(prefix + "notify", false);
    }

    @Bean
    Queue durableQueue() {
        return new Queue(prefix + "durable");
    }

    @Bean
    Queue autoDeleteQueue() {
        return new AnonymousQueue();
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(prefix + "direct-exchange");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(prefix + "fanout-exchange");
    }

    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange(prefix + "headers-exchange");
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(prefix + "topic-exchange");
    }

    @Bean
    Binding bindingDirect(Queue defaultQueue, DirectExchange exchange) {
        return BindingBuilder.bind(defaultQueue).to(exchange).with(defaultQueue.getName());
    }

    @Bean
    Binding bindingFanout(Queue defaultQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(defaultQueue).to(exchange);
    }

    @Bean
    Binding bindingNotify(Queue notifyQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(notifyQueue).to(exchange);
    }

//    @Bean
//    Binding bindingHeaders(Queue defaultQueue, HeadersExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).where();
//    }

    @Bean
    Binding bindingTopic(Queue defaultQueue, TopicExchange exchange) {
        return BindingBuilder.bind(defaultQueue).to(exchange).with(defaultQueue.getName());
    }

//    @Bean
//    SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory,
//                                                    MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(prefix + "default");
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }
//
//    @Bean
//    MessageListenerAdapter listenerAdapter(MessageReceiver receiver) {
//        return new MessageListenerAdapter(receiver, "receive");
//    }
}
