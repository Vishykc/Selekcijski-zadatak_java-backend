package com.github.Selekcijski.zadatak_java.backend.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="Osobe")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Osoba {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ime;

    private String prezime;

    private LocalDate datumRodjenja;


    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "Osoba(id=" + this.getId() +", ime=" + this.getIme() +", prezime=" + this.getPrezime() +
        ", datumRodjenja=" + this.getDatumRodjenja().format(formatter) + ")";
    }
}
