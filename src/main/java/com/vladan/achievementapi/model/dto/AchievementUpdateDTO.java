package com.vladan.achievementapi.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AchievementUpdateDTO {

    @NotBlank
    @Size(max = 100)
    private String displayName;

    @NotBlank
    @Size(max = 500)
    private String description;

    @Size(max = 512)
    @URL
    private String icon;
}
