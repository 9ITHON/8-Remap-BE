package com.example.ReMap.domain.tema;

import com.example.ReMap.domain.cast.Cast;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tema")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tema {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @ManyToMany(mappedBy = "temas")
    private List<Cast> casts = new ArrayList<>();

    @Builder          // name 하나만 받도록
    public Tema(String name) {
        this.name = name;
    }
}
