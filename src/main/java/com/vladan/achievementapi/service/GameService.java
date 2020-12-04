package com.vladan.achievementapi.service;

import com.vladan.achievementapi.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService {

    private GameRepository repository;

    @Autowired
    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public boolean exists(UUID id) {
        return repository.existsById(id);
    }

}
