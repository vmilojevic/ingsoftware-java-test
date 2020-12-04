package com.vladan.achievementapi.controller;

import com.vladan.achievementapi.model.dto.AchievementResponseDTO;
import com.vladan.achievementapi.model.dto.AchievementUpdateDTO;
import com.vladan.achievementapi.model.dto.CreateAchievementDTO;
import com.vladan.achievementapi.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/achievements", produces = APPLICATION_JSON_VALUE)
public class AchievementController {

    private AchievementService achievementService;

    @Autowired
    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @PostMapping
    public ResponseEntity<AchievementResponseDTO> save(@Valid @RequestBody CreateAchievementDTO createAchievementDTO) {
        AchievementResponseDTO achievementResponse = achievementService.save(createAchievementDTO);
        return new ResponseEntity<>(achievementResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AchievementResponseDTO> get(@PathVariable("id") UUID id) {
        AchievementResponseDTO achievementResponse = achievementService.getById(id);
        return ResponseEntity.ok(achievementResponse);
    }

    @GetMapping
    public ResponseEntity<List<AchievementResponseDTO>> getByGameId(@RequestParam(name = "gameId") UUID gameId) {
        List<AchievementResponseDTO> achievementResponses = achievementService.getAllByGameId(gameId);
        return ResponseEntity.ok(achievementResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        achievementService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AchievementResponseDTO> update(@PathVariable("id") UUID id,
                                                         @Valid @RequestBody AchievementUpdateDTO achievementUpdateDTO) {
        AchievementResponseDTO achievementResponse = achievementService.update(id, achievementUpdateDTO);
        return ResponseEntity.ok(achievementResponse);
    }

    @PatchMapping("/test/{id}")
    public ResponseEntity<AchievementResponseDTO> testUpdate(@PathVariable("id") UUID id,
                                                           @Valid @RequestBody AchievementUpdateDTO achievementUpdateDTO) {
        AchievementResponseDTO achievementResponse = achievementService.testUpdate(id, achievementUpdateDTO);
        return new ResponseEntity<>(achievementResponse, HttpStatus.OK);
    }

    @PostMapping("/test")
    public ResponseEntity<AchievementResponseDTO> testSave(@Valid @RequestBody CreateAchievementDTO createAchievementDTO) {
        AchievementResponseDTO achievementResponse = achievementService.testSave(createAchievementDTO);
        return new ResponseEntity<>(achievementResponse, HttpStatus.CREATED);
    }
}

