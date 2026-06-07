package com.music.music_player.repository;

import com.music.music_player.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    Optional<Album> findByTitre(String titre);

    boolean existsByTitre(String titre);

}
