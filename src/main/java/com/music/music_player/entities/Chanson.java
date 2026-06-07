package com.music.music_player.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chansons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chanson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String titre;

    private String fichierAudio;

    @ManyToOne
    @JoinColumn(name = "album_id")
    @JsonBackReference
    private Album album;
}
