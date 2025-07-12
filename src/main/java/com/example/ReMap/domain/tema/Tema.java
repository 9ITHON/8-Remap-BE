package com.example.ReMap.domain.tema;

import jakarta.persistence.*;

@Entity
@Table(name = "tema")
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}

