package com.music.music_player.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "artistes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Artiste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nom;

    private String description;

    private String image;

    @OneToMany(mappedBy = "artiste",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Album> albums;
}
