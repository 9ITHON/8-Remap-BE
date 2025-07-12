package com.example.ReMap.domain.cast;

import com.example.ReMap.domain.tema.Tema;
import jakarta.persistence.*;

@Entity
@Table(name = "cast_tema")
public class CastTema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cast_id")
    private Cast cast;

    @ManyToOne
    @JoinColumn(name = "tema_id")
    private Tema tema;
}
