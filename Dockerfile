# Estágio 1: Build (Compilação)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte e compila
COPY src ./src
RUN mvn clean package -DskipTests

# ---
# Estágio 2: Imagem Final (Execução)
# Usamos uma imagem leve (JRE) apenas para rodar o .jar
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Cria um usuário não-root por segurança
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Copia o .jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# O Render injeta a porta na variável PORT.
# O Spring Boot lê automaticamente a variável SERVER_PORT se definida.
ENV SERVER_PORT=8080
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]