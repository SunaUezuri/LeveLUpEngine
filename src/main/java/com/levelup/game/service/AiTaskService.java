package com.levelup.game.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.levelup.game.dto.task.CreateTaskDto;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiTaskService {

    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    // O Spring injeta o 'builder' já configurado com a API KEY do application.properties.
    // Não precisamos manusear a string da chave aqui manualmente.
    public AiTaskService(ChatClient.Builder chatClientBuilder, ObjectMapper objectMapper) {
        this.chatClient = chatClientBuilder.build();
        this.objectMapper = objectMapper;
    }

    /*
     Feature 1: GERAÇÃO CRIATIVA (Brainstorming)
     Cria uma tarefa do zero a partir de um tema simples.
    */
    public CreateTaskDto generateTaskSuggestion(String topic) {
        String promptText = """
            Aja como um especialista em gamificação corporativa.
            Crie uma sugestão de tarefa para o tema: %s.
            
            Responda APENAS um JSON (sem markdown) com:
            - title (max 50 chars)
            - description (max 200 chars)
            - pointsValue (inteiro 10-100)
            - taskType ('SOCIAL', 'WELLNESS' ou 'WORK')
            """.formatted(topic);

        return callAiAndParse(promptText);
    }

    /*
      Feature 2: SMART POINTS ESTIMATOR
    */
    public CreateTaskDto analyzeAndEstimatePoints(String title, String rawDescription) {
        String promptText = """
            Analise a seguinte tarefa corporativa e defina sua pontuação justa e categoria.
            
            Título: %s
            Descrição: %s
            
            Regras de Pontuação:
            - Baixo esforço (ex: ler email): 10-30 pts
            - Médio esforço (ex: reunião, feedback): 31-60 pts
            - Alto esforço (ex: projeto, curso): 61-100 pts
            
            Responda APENAS um JSON (sem markdown) com:
            - title (mantenha o original)
            - description (melhore o texto para ficar mais engajador, max 200 chars)
            - pointsValue (sua estimativa)
            - taskType (classifique em 'SOCIAL', 'WELLNESS' ou 'WORK')
            """.formatted(title, rawDescription);

        return callAiAndParse(promptText);
    }

    private CreateTaskDto callAiAndParse(String prompt) {
        try {
            String jsonResponse = chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();

            String cleanJson = jsonResponse.replace("```json", "").replace("```", "").trim();

            JsonNode root = objectMapper.readTree(cleanJson);

            return new CreateTaskDto(
                    root.path("title").asText("Tarefa"),
                    root.path("description").asText("Descrição gerada pela IA"),
                    root.path("pointsValue").asInt(10),
                    root.path("taskType").asText("WORK")
            );

        } catch (Exception e) {
            System.err.println("Erro na IA: " + e.getMessage());
            return new CreateTaskDto(
                    "Erro na IA",
                    "Não foi possível processar a solicitação. Tente manualmente.",
                    0,
                    "WORK"
            );
        }
    }
}
