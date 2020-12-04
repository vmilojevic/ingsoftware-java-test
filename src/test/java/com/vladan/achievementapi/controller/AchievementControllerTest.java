package com.vladan.achievementapi.controller;

import com.google.gson.Gson;
import com.vladan.achievementapi.model.dto.AchievementResponseDTO;
import com.vladan.achievementapi.model.dto.CreateAchievementDTO;
import com.vladan.achievementapi.service.AchievementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AchievementController.class)
public class AchievementControllerTest {

    private static final Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AchievementService achievementService;

    @Test
    public void shouldReturnHttpCreated() throws Exception {
        CreateAchievementDTO createAchievementDTO = new CreateAchievementDTO();
        createAchievementDTO.setDisplayName("Display name");
        createAchievementDTO.setDescription("Description");
        createAchievementDTO.setGameId(UUID.randomUUID());

        given(achievementService.save(createAchievementDTO)).willReturn(new AchievementResponseDTO());

        mockMvc.perform(post("/api/achievements")
                .content(gson.toJson(createAchievementDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnBadRequestWhenInvalidCreationParameters() throws Exception {
        CreateAchievementDTO createAchievementDTO = new CreateAchievementDTO();
        createAchievementDTO.setDisplayName("Display name");
        createAchievementDTO.setDescription("Description");
        createAchievementDTO.setGameId(UUID.randomUUID());
        createAchievementDTO.setIcon("Not a link");

        mockMvc.perform(post("/api/achievements")
                .content(gson.toJson(createAchievementDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
