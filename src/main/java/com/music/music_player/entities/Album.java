package com.music.music_player.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "albums")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String titre;

    private String image;

    @ManyToOne
    @JoinColumn(name = "artiste_id")
    @JsonBackReference
    private Artiste artiste;

    @OneToMany(mappedBy = "album",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Chanson> chansons;
}