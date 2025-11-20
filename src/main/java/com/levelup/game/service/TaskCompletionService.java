package com.levelup.game.service;

import com.levelup.game.dto.completion.CompleteTaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskCompletionService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${levelup.rabbitmq.queue}")
    private String queueName;

    public void completeTask(CompleteTaskDto dto) {
        // Envia para a fila do RabbitMQ
        // O Jackson2JsonMessageConverter (configurado no RabbitMQConfig)
        // vai transformar esse objeto Java em JSON automaticamente.
        rabbitTemplate.convertAndSend(queueName, dto);

        System.out.println("Mensagem enviada para fila: " + dto);
    }
}
