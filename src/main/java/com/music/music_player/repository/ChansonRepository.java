package com.music.music_player.repository;

import com.music.music_player.entities.Chanson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChansonRepository extends JpaRepository<Chanson, Long> {
    //
    Optional<Chanson> findByTitre(String titre);

    boolean existsByTitre(String titre);
}