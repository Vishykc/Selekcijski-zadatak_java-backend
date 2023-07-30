package com.github.Selekcijski.zadatak_java.backend.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// pvdje koristim Lombok jer je u drugim firmama bila takva praksa, ali osobno mi se vise svida napisati eksplicitno sve
@Entity
@Table(name="Osobe")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Osoba {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //ovdje nije vazna strategija pa stavljam AUTO
    private Long id;

    private String ime;

    private String prezime;

    private LocalDate datumRodjenja;


    //override toString-a obzirom da LocalDate po defaultu ispisuje drugi format datuma
    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "Osoba(id=" + this.getId() +", ime=" + this.getIme() +", prezime=" + this.getPrezime() +
        ", datumRodjenja=" + this.getDatumRodjenja().format(formatter) + ")";
    }
}
