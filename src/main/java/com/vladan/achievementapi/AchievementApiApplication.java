package com.vladan.achievementapi;

import com.vladan.achievementapi.model.entity.Game;
import com.vladan.achievementapi.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AchievementApiApplication {

    @Autowired
    private GameRepository gameRepository;

    public static void main(String[] args) {
        SpringApplication.run(AchievementApiApplication.class, args);
    }

    // If generating more game is needed, just uncomment this lines
//    @PostConstruct
//    public void init() {
//        gameRepository.save(new Game("World of Warcraft"));
//        gameRepository.save(new Game("Warcraft 3"));
//        gameRepository.save(new Game("Brawl Stars"));
//        gameRepository.save(new Game("Need For Speed"));
//    }

}
