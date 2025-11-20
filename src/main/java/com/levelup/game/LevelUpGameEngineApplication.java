package com.levelup.game;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class LevelUpGameEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(LevelUpGameEngineApplication.class, args);
	}

}
