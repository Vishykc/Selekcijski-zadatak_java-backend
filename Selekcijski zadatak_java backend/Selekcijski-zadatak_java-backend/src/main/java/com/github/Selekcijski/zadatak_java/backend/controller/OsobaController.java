package com.github.Selekcijski.zadatak_java.backend.controller;

import com.github.Selekcijski.zadatak_java.backend.Model.Osoba;
import com.github.Selekcijski.zadatak_java.backend.repo.OsobaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class OsobaController {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String line = "";

    @Autowired
    private OsobaRepo osobaRepo;

    @GetMapping("/getAllOsobe")
    public ResponseEntity<List<Osoba>> getAllOsobe() {

        try {
            List<Osoba> osobaList = new ArrayList<>();
            osobaRepo.findAll().forEach(osobaList::add);

            if(osobaList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(osobaList, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //prilikom poziva metode, potrebno je u body staviti tip text te ime csv-a BEZ ekstenzije
    @PostMapping(value = "/addOsobe")
    public ResponseEntity<Object> addOsobe(@RequestBody String name) throws IOException {

        Osoba osobaObj = new Osoba();

        List<String> CSVData = new ArrayList<>();

        int i_Ime = -1, i_Prezime = -1, i_DatumRodjenja = -1; //indeksi imena, prezimena i datuma rodjenja

        //ovaj path se vec kreirao, potrebno je napraviti .csv fajl u njemu
        String filePathString = "C:/Selekcijski zadatak_java backend_pomocni_resursi/csv_fajlovi/" +
                name + ".csv";

        BufferedReader br = new BufferedReader(new FileReader(filePathString));

        while((line = br.readLine()) != null) {

            String[] values = line.split("\\s*,\\s*"); //tekst odvojen opcionalnim whitespace-ovima i obveznim
            List<String> lineList = Arrays.asList(values);  // zarezom
            CSVData.addAll(lineList);
        }
            // pronadi indekse "headera" ime, prezime i datumRodjenja te je za svaki indeks i indeks i + 3 * k, gdje je
           // k prirodan broj element iz danog headera
            int CSV_Size = CSVData.size();
            for(int i = 0; i < CSV_Size; i++) {
                if (CSVData.get(i).equalsIgnoreCase("ime")) {
                    i_Ime = i;
                    continue;
                }

                if (CSVData.get(i).equalsIgnoreCase("prezime")) {
                    i_Prezime = i;
                    continue;
                }

                if (CSVData.get(i).equalsIgnoreCase("datumRodjenja")) {
                    i_DatumRodjenja = i;
                    continue;
                }

                if(i_Ime > - 1 && i_Prezime > -1 && i_DatumRodjenja > -1) break;
            }

            // puni redom osobe te ih ispisuje sad kad znamo indekse headera
            for(int i = 3; i < CSV_Size; i++) {

                if(i % 3 == i_Ime) {
                    osobaObj.setIme(CSVData.get(i));
                }

                if(i % 3 == i_Prezime) {
                    osobaObj.setPrezime(CSVData.get(i));
                }

                if(i % 3 == i_DatumRodjenja) {
                    LocalDate localDate = LocalDate.parse(CSVData.get(i), formatter);
                    osobaObj.setDatumRodjenja(localDate);
                }

                if(osobaObj.getIme() != null && osobaObj.getPrezime() != null && osobaObj.getDatumRodjenja() != null) {
                    osobaRepo.save(osobaObj);

                    System.out.println(osobaObj);

                    osobaObj = new Osoba();
                }
            }

        return new ResponseEntity<>(HttpStatus.OK); // vraca jednostavan status ok iako bi moglo i listu objekata
    }
}