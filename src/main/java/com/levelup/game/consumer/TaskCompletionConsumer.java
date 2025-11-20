package com.levelup.game.consumer;

import com.levelup.game.dto.completion.CompleteTaskDto;
import com.levelup.game.repository.TaskCompletionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskCompletionConsumer {

    private final TaskCompletionRepository completionRepository;

    @RabbitListener(queues = "${levelup.rabbitmq.queue}")
    @Transactional
    public void receiveCompletionMessage(@Payload CompleteTaskDto dto) {
        try {
            log.info("🐇 Mensagem recebida da fila: User={}, Task={}", dto.userId(), dto.taskId());

            completionRepository.completeTaskProcedure(
                    dto.userId(),
                    dto.taskId(),
                    null
            );

            log.info("✅ Tarefa concluída e processada com sucesso no banco!");
        } catch (Exception e) {
            log.error("❌ Erro ao processar mensagem da fila: {}", e.getMessage());
        }
    }
}
