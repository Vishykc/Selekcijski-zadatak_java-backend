package com.github.Selekcijski.zadatak_java.backend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name="Osobe")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Osoba {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ime;

    private String prezime;

    private LocalDate datumRodjenja;



}
