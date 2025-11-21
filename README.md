<div align="center">
  
# 🎮 LevelUp Game Engine (Java)

**O Core de Gamificação e Gestão de Engajamento Corporativo**

</div>

<p align="center">
  <img alt="Java 17" src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk">
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=springboot">
  <img alt="Oracle" src="https://img.shields.io/badge/Oracle-Database-red?style=for-the-badge&logo=oracle">
  <img alt="DeepSeek AI" src="https://img.shields.io/badge/AI-DeepSeek-blue?style=for-the-badge&logo=openai">
  <img alt="Thymeleaf" src="https://img.shields.io/badge/Thymeleaf-UI-green?style=for-the-badge&logo=thymeleaf">
  <img alt="RabbitMQ" src="https://img.shields.io/badge/RabbitMQ-Messaging-orange?style=for-the-badge&logo=rabbitmq">
</p>

---

## 🧑‍💻 Autores

<div align="center">

| Nome | RM |
| :--- | :--- |
| **Wesley Sena dos Santos** | 558043 |
| **Vanessa Yukari Iwamoto** | 558092 |
| **Samara Victoria Ferraz dos Santos** | 558719 |

</div>

---

## 🔗 Links e Demonstração

<div align="center">

| Tipo | Acesso |
| :--- | :--- |
| **🚀 Aplicação (Deploy)** | [![Deploy](https://img.shields.io/badge/Acessar-Aplicação_Online-2ea44f?style=for-the-badge&logo=rocket)](COLOQUE_O_LINK_DO_RENDER_OU_AZURE_AQUI) |
| **📺 Vídeo Demonstração** | [![Demo](https://img.shields.io/badge/YouTube-Ver_Demo-FF0000?style=for-the-badge&logo=youtube)](COLOQUE_O_LINK_DO_YOUTUBE_AQUI) |
| **💡 Vídeo Pitch** | [![Pitch](https://img.shields.io/badge/YouTube-Ver_Pitch-FF0000?style=for-the-badge&logo=youtube)](COLOQUE_O_LINK_DO_PITCH_AQUI) |
| **🔗 API de Recompensas (.NET)** | [![Repo .NET](https://img.shields.io/badge/GitHub-Repositório_.NET-512BD4?style=for-the-badge&logo=dotnet)](https://github.com/SunaUezuri/LevelUp) |

</div>

---

## 🔐 Credenciais de Acesso (Demo)

Para facilitar os testes no ambiente de deploy, utilize os usuários pré-cadastrados abaixo.

| Perfil | Email | Senha | Permissões |
| :--- | :--- | :--- | :--- |
| **👑 ADMIN** | `wes@levelup.com` | `NovaSenha@123` | Acesso total: Criar tarefas, gerenciar times e validar missões. |
| **👤 USER** | `c@levelup.com` | `NovaSenha@123` | Acesso padrão: Dashboard pessoal, ranking e loja de recompensas. |

> **Nota:** O banco de dados pode ser resetado periodicamente. Caso o login falhe, entre em contato com os desenvolvedores.
> **Adendo:** Caso queira inserir novas recompensas, resgatar elas para ver como fica no painel, e etc acesse o repo do projeto de .NET e use os endpoints indicados lá.

---

## 🎯 Sobre o Projeto

O **LevelUp Game Engine** é o módulo central de gamificação da solução LevelUp. Enquanto a API .NET gerencia o catálogo financeiro e de recompensas, esta aplicação Java/Spring Boot atua como o **motor de engajamento** e **interface administrativa**.

Ele é responsável por transformar atividades corporativas monótonas (tarefas, reuniões, feedbacks) em missões de RPG, calculando XP, gerenciando níveis (de *Noob* a *Cyberpunk Legend*) e fornecendo ferramentas de IA para gestores criarem dinâmicas de equipe.

### Diferenciais Técnicos
* **IA Generativa Integrada:** Utiliza o modelo **DeepSeek** para sugerir tarefas criativas e calcular automaticamente a pontuação justa baseada na descrição da atividade.
* **Database Centric Logic:** Regras críticas de inserção e auditoria são processadas diretamente no Oracle Database via packages PL/SQL (`PKG_LEVELUP_APP`).
* **Arquitetura Orientada a Eventos:** Processamento assíncrono de conclusão de tarefas via **RabbitMQ**.

---

## ✨ Funcionalidades Principais

### 🤖 Integração com IA (Spring AI + DeepSeek)
O sistema auxilia os gestores na criação de tarefas:
* **Brainstorming Criativo:** O gestor insere um tema (ex: "Sustentabilidade") e a IA gera uma missão completa com título, descrição e tipo.
* **Smart Points Estimator:** A IA analisa a dificuldade da descrição da tarefa e sugere uma pontuação justa (XP) automaticamente.

### ⚔️ Sistema de RPG Corporativo
* **Cálculo de Nível:** Progressão automática baseada em XP acumulado.
  * *Ranks:* Noob -> Padawan -> Ranger -> Jedi Knight -> Wizard -> Cyberpunk Legend.
* **Leaderboard:** Ranking em tempo real dos funcionários mais engajados.
* **Times e Guildas:** Gestão de equipes para competições entre setores.

### 📊 Dashboard Administrativo (Server-Side Rendering)
Interface completa renderizada com **Thymeleaf** para:
* Gestão de Usuários e Times.
* Atribuição de Tarefas (Individuais ou por Equipe).
* Validação de Missões.
* Auditoria de Resgates.

### 🗄️ Banco de Dados Robusto (PL/SQL)
O projeto utiliza o poder do Oracle para garantir integridade:
* **Stored Procedures:** Toda criação de tarefas, usuários e atribuições passa pela package `PKG_LEVELUP_APP`.
* **Triggers de Auditoria:** Monitoramento automático (`TB_LEVELUP_AUDIT_LOGS`) de todas as operações DML nas tabelas principais.
* **JSON Export:** Funções nativas do banco para gerar relatórios JSON de perfil de usuário.

---

## 🛠️ Tech Stack

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3.5.8
  * *Web:* Spring MVC
  * *Data:* Spring Data JPA
  * *Security:* Spring Security (Auth & Roles)
  * *AI:* Spring AI (DeepSeek integration)
* **Frontend:** Thymeleaf + Bootstrap
* **Banco de Dados:** Oracle Database 19c/21c
* **Mensageria:** RabbitMQ (AMQP)
* **Migração:** Flyway
* **Ferramentas:** Lombok, Maven

---

## 🏗️ Arquitetura e Fluxo de Dados

O projeto segue uma arquitetura em camadas (MVC) integrada a serviços externos.

```mermaid
graph TD
    User((Usuário/Admin)) -->|HTTP/Browser| Controller[Spring MVC Controllers]
    
    subgraph "LevelUp Game Engine (Java)"
        Controller --> Service[Service Layer]
        Service -->|JPA/Hibernate| Repository[Data Repository]
        Service -->|Prompt| AI[Spring AI Client]
        Service -->|Publish/Consume| MQ[RabbitMQ Producer/Consumer]
    end
    
    subgraph "Infraestrutura"
        Repository -->|Call Procedures| Oracle[(Oracle Database)]
        Oracle -->|Triggers| Audit[Tabela de Auditoria]
        AI -->|API Call| DeepSeek[DeepSeek API]
        MQ -->|Queue: levelup.quests| RabbitServer[RabbitMQ Server]
    end
```

## 🚀 Como Executar

### 1. Pré-requisitos
* **Java 17** (JDK) instalado.
* **Maven** instalado.
* **Oracle Database** (local ou nuvem) acessível.
* **RabbitMQ Server** rodando (Docker ou Service).
* **Chave de API do DeepSeek** (opcional para funcionalidades de IA, mas recomendado).

### 2. Configuração de Variáveis de Ambiente
O projeto utiliza variáveis de ambiente para não expor credenciais no código. Configure-as no seu sistema operacional ou no arquivo de configuração da sua IDE (IntelliJ/Eclipse/VS Code).

| Variável | Descrição | Exemplo |
| :--- | :--- | :--- |
| `DB_LINK` | JDBC URL de conexão Oracle | `jdbc:oracle:thin:@//localhost:1521/XE` |
| `DB_USER` | Usuário do Banco de Dados | `SYSTEM` |
| `DB_PASSWORD` | Senha do Banco de Dados | `sua_senha_oracle` |
| `DEEPSEEK_API` | API Key da DeepSeek (IA) | `sk-abc123...` |
| `RABBIT_ADD` | Endereço AMQP do RabbitMQ | `amqp://guest:guest@localhost:5672` |

> **Nota:** Se preferir testar rápido, você pode substituir os valores `${...}` diretamente no arquivo `src/main/resources/application.properties`, mas **não comite** o arquivo com suas senhas!

### 3. Setup do Banco de Dados
Para o projeto funcionar corretamente localmente acesse o projeto de .NET no link do início e execute as migrations dele, pois ambos funcionam com o mesmo banco

### 4. Executando a Aplicação
Abra o terminal na raiz do projeto (onde está o `pom.xml`) e execute:

```bash
mvn spring-boot:run
```

A aplicação subirá na porta padrão: http://localhost:8080

## 📖 Guia de Uso

### 🔐 Login e Acesso
Acesse `/login`. O sistema não possui registro aberto na tela de login (para segurança corporativa).

* **Primeiro Acesso:** Insira um usuário manualmente no banco de dados usando a procedure `PR_CREATE_USER` ou utilize a API de Registro (se estiver integrada).
* **Perfis:**
    * **ROLE_ADMIN:** Tem acesso total ao menu "Admin" (Gestão de Tarefas, Recompensas, Usuários).
    * **ROLE_USER:** Acesso apenas ao Dashboard pessoal, Lista de Tarefas e Loja.

### 🛠️ Funcionalidades do Admin
1. **Criar Tarefas com IA:**
    * Vá em **Admin > Tarefas > Nova**.
    * Clique no botão "✨ Gerar com IA".
    * Digite um tema (ex: "Code Review Semanal") e veja a IA preencher o título, descrição e sugerir o XP.
2. **Atribuir Missões:**
    * Vá em **Admin > Atribuições**.
    * Você pode atribuir uma tarefa para um usuário específico ou para um **Time** inteiro (ex: "Time Backend").

### 🎮 Funcionalidades do Jogador
1. **Dashboard:** Acompanhe sua barra de progresso até o próximo nível e veja sua posição no ranking geral.
2. **Minhas Tarefas:** Em `/tasks/my-tasks`, veja as missões pendentes. Ao concluir, clique em "Concluir".
    * *Back-end:* Isso dispara um evento para o **RabbitMQ**, que processa a pontuação assincronamente.

---

## 🧪 Estrutura do Banco de Dados (PL/SQL)

O projeto utiliza fortemente recursos nativos do Oracle para garantir performance e integridade. A lógica reside na package `PKG_LEVELUP_APP`.

### Principais Procedures
* `PR_CREATE_TASK`: Cria tarefas validando tipos permitidos ('WORK', 'SOCIAL', 'WELLNESS').
* `PR_CREATE_TASK_ASSIGNMENT`: Garante a regra de exclusividade (ou atribui para User, ou para Team, nunca ambos e nem nenhum).
* `PR_CREATE_REWARD_REDEMPTION`: Realiza a transação de compra na loja (Débito de Pontos + Baixa de Estoque) de forma atômica.
* `FN_BUILD_USER_PROFILE_JSON`: Função que retorna um JSON CLOB complexo com todo o histórico do usuário, pronto para APIs de Analytics.

### Auditoria
Toda alteração (`INSERT`, `UPDATE`, `DELETE`) nas tabelas principais dispara um **Trigger** que grava um registro na tabela `TB_LEVELUP_AUDIT_LOGS`, contendo:
* Quem alterou (Usuário do banco/App).
* Data/Hora.
* Tipo de Operação.
* ID do registro afetado.

---

## 🤝 Contribuição

1. Faça um **Fork** do projeto.
2. Crie uma **Branch** para sua feature (`git checkout -b feature/NovaFeature`).
3. Faça o **Commit** (`git commit -m 'Adicionando nova feature'`).
4. Faça o **Push** (`git push origin feature/NovaFeature`).
5. Abra um **Pull Request**.

---

<div align="center">
  
**FIAP - Global Solution - 2025**
<br>
*Desenvolvido com ❤️, Java e muito Café ☕*
  
</div>
