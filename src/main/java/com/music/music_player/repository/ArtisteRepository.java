package com.music.music_player.repository;

import com.music.music_player.entities.Artiste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtisteRepository extends JpaRepository<Artiste, Long> {

    Optional<Artiste> findByNom(String nom);

    boolean existsByNom(String nom);

}
