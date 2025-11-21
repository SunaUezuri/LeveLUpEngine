package com.levelup.game.consumer;

import com.levelup.game.dto.completion.CompleteTaskDto;
import com.levelup.game.repository.TaskCompletionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskCompletionConsumer {

    private final TaskCompletionRepository completionRepository;

    @RabbitListener(queues = "${levelup.rabbitmq.queue}")
    public void receiveCompletionMessage(@Payload CompleteTaskDto dto) {
        try {
            log.info("🐇 Mensagem recebida da fila: User={}, Task={}", dto.userId(), dto.taskId());

            completionRepository.completeTaskProcedure(
                    dto.userId(),
                    dto.taskId(),
                    null
            );

            log.info("✅ Tarefa concluída e processada com sucesso no banco!");
        } catch (DataIntegrityViolationException e) {
            log.error("⛔ Erro de integridade: A Tarefa ID {} ou Usuário ID {} não existem mais no banco. Descartando mensagem.",
                    dto.taskId(), dto.userId());
        } catch (Exception e) {
            log.error("❌ Erro inesperado: {}", e.getMessage());
        }
    }
}
