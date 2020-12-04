package com.vladan.achievementapi.repository;

import com.vladan.achievementapi.model.entity.Achievement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AchievementRepository extends CrudRepository<Achievement, UUID> {

    List<Achievement> findAllByGameIdOrderByDisplayOrderAsc(UUID gameId);

//    Page<Achievement> findAllByGameId(UUID gameId, Pageable pageable);

    boolean existsByGameIdAndDisplayName(UUID gameId, String displayName);

}
