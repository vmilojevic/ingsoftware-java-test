package com.vladan.achievementapi.service;

import com.vladan.achievementapi.exception.DuplicateNameException;
import com.vladan.achievementapi.model.dto.AchievementResponseDTO;
import com.vladan.achievementapi.model.dto.AchievementUpdateDTO;
import com.vladan.achievementapi.model.dto.CreateAchievementDTO;
import com.vladan.achievementapi.model.entity.Achievement;
import com.vladan.achievementapi.repository.AchievementRepository;
import com.vladan.achievementapi.util.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AchievementService {

    private AchievementRepository repository;
    private GameService gameService;

    @Autowired
    public AchievementService(AchievementRepository repository, GameService gameService) {
        this.repository = repository;
        this.gameService = gameService;
    }

    @Transactional
    public AchievementResponseDTO save(CreateAchievementDTO createAchievementDTO) {
        if (gameService.exists(createAchievementDTO.getGameId())) {
            if (repository.existsByGameIdAndDisplayName(createAchievementDTO.getGameId(), createAchievementDTO.getDisplayName()))
                throw new DuplicateNameException("Achievement with name " + createAchievementDTO.getDisplayName() +
                        " already exists for game " + createAchievementDTO.getGameId());

            Achievement achievement = MapperUtils.mapCreateAchievementDtoToAchievement(createAchievementDTO);
            achievement.setCreatedOn(LocalDateTime.now());

            // automatically set display order to highest value
            List<Achievement> achievements = repository.findAllByGameIdOrderByDisplayOrderAsc(
                    createAchievementDTO.getGameId());
            if (CollectionUtils.isEmpty(achievements)) {
                achievement.setDisplayOrder(1L);
            } else {
                achievement.setDisplayOrder(achievements.get(achievements.size() - 1).getDisplayOrder() + 1);
            }

            return MapperUtils.mapAchievementToAchievementResponseDTO(repository.save(achievement));
        } else {
            throw new EntityNotFoundException("Game " + createAchievementDTO.getGameId() + " not found");
        }
    }

    @Transactional
    public void delete(UUID id) {
        Achievement achievement = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Achievement " + id + " not found"));
        repository.delete(achievement);
    }

    @Transactional(readOnly = true)
    public List<AchievementResponseDTO> getAllByGameId(UUID gameId) {
        if (gameService.exists(gameId)) {
            List<Achievement> achievements = repository.findAllByGameIdOrderByDisplayOrderAsc(gameId);

            return achievements.stream()
                    .map(MapperUtils::mapAchievementToAchievementResponseDTO)
                    .collect(Collectors.toList());
        } else {
            throw new EntityNotFoundException("Game " + gameId + " not found");
        }
    }

    @Transactional(readOnly = true)
    public AchievementResponseDTO getById(UUID id) {
        Achievement achievement = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Achievement " + id + " not found"));

        return MapperUtils.mapAchievementToAchievementResponseDTO(achievement);
    }

    @Transactional
    public AchievementResponseDTO update(UUID id, AchievementUpdateDTO achievementUpdateDTO) {
        Achievement achievement = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Achievement " + id + " not found"));

        if (!repository.existsByGameIdAndDisplayName(achievement.getGameId(), achievementUpdateDTO.getDisplayName())) {
            achievement.setDisplayName(achievementUpdateDTO.getDisplayName());
            achievement.setDescription(achievementUpdateDTO.getDescription());
            achievement.setIcon(achievementUpdateDTO.getIcon());
            achievement.setUpdatedOn(LocalDateTime.now());

            return MapperUtils.mapAchievementToAchievementResponseDTO(repository.save(achievement));
        } else {
            throw new DuplicateNameException("Achievement with name " + achievementUpdateDTO.getDisplayName() +
                    " already exists for game " + achievement.getGameId());
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public AchievementResponseDTO testUpdate(UUID id, AchievementUpdateDTO achievementUpdateDTO) {
        Achievement achievement = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Achievement " + id + " not found"));

        achievement.setDisplayName(achievementUpdateDTO.getDisplayName());
//        repository.save(achievement);
        achievement.setDescription(achievementUpdateDTO.getDescription());
        return MapperUtils.mapAchievementToAchievementResponseDTO(achievement);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public AchievementResponseDTO testSave(CreateAchievementDTO createAchievementDTO) {
        Achievement achievement = MapperUtils.mapCreateAchievementDtoToAchievement(createAchievementDTO);
        achievement.setCreatedOn(LocalDateTime.now());

//        repository.save(achievement);
        achievement.setDisplayName("Drugi put setujem");
        return MapperUtils.mapAchievementToAchievementResponseDTO(achievement);
    }
}
