package com.vladan.achievementapi.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AchievementResponseDTO {

    private UUID id;
    private UUID gameId;
    private String displayName;
    private String description;
    private String icon;

}
