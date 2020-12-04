package com.vladan.achievementapi.service;

import com.vladan.achievementapi.model.dto.AchievementUpdateDTO;
import com.vladan.achievementapi.model.dto.CreateAchievementDTO;
import com.vladan.achievementapi.repository.AchievementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AchievementServiceTest {

    @InjectMocks
    private AchievementService achievementService;

    @Mock
    private AchievementRepository achievementRepository;

    @Mock
    private GameService gameService;

    @Test
    public void shouldThrowErrorWhenSaveAchievementWithInvalidGameId() {
        CreateAchievementDTO createAchievementDTO = new CreateAchievementDTO();
        createAchievementDTO.setGameId(UUID.randomUUID());

        given(gameService.exists(createAchievementDTO.getGameId())).willReturn(false);

        assertThrows(EntityNotFoundException.class, () -> achievementService.save(createAchievementDTO));
    }


    @Test
    public void shouldThrowErrorWhenGetAllByGameIdWithInvalidGameId() {
        UUID gameId = UUID.randomUUID();

        given(gameService.exists(gameId)).willReturn(false);

        assertThrows(EntityNotFoundException.class, () -> achievementService.getAllByGameId(gameId));
    }

    @Test
    public void shouldThrowErrorWhenDeleteWithInvalidId() {
        UUID id = UUID.randomUUID();

        given(achievementRepository.findById(id)).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> achievementService.delete(id));
    }

    @Test
    public void shouldThrowErrorWhenGetByIdWithInvalidId() {
        UUID id = UUID.randomUUID();

        given(achievementRepository.findById(id)).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> achievementService.getById(id));
    }

    @Test
    public void shouldThrowErrorWhenUpdateWithInvalidId() {
        UUID id = UUID.randomUUID();
        AchievementUpdateDTO achievementUpdateDTO = new AchievementUpdateDTO();

        given(achievementRepository.findById(id)).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> achievementService.update(id, achievementUpdateDTO));
    }

}
