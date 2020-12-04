package com.vladan.achievementapi.util;

import com.vladan.achievementapi.model.dto.AchievementResponseDTO;
import com.vladan.achievementapi.model.dto.CreateAchievementDTO;
import com.vladan.achievementapi.model.entity.Achievement;

public class MapperUtils {

    public static Achievement mapCreateAchievementDtoToAchievement(CreateAchievementDTO createAchievementDTO) {
        Achievement achievement = new Achievement();
        achievement.setDisplayName(createAchievementDTO.getDisplayName());
        achievement.setDescription(createAchievementDTO.getDescription());
        achievement.setIcon(createAchievementDTO.getIcon());
        achievement.setGameId(createAchievementDTO.getGameId());

        return achievement;
    }

    public static AchievementResponseDTO mapAchievementToAchievementResponseDTO(Achievement achievement) {
        AchievementResponseDTO achievementResponseDTO = new AchievementResponseDTO();
        achievementResponseDTO.setId(achievement.getId());
        achievementResponseDTO.setDisplayName(achievement.getDisplayName());
        achievementResponseDTO.setDescription(achievement.getDescription());
        achievementResponseDTO.setIcon(achievement.getIcon());
        achievementResponseDTO.setGameId(achievement.getGameId());

        return achievementResponseDTO;
    }

}
