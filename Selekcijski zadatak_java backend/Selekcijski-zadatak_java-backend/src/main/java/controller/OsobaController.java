package controller;

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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Long.parseLong;

@RestController
public class OsobaController {

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

    @PostMapping(value = "/addOsobe")
    public void addOsobe(@RequestBody String name) throws IOException {

        int i_Ime = -1, i_Prezime = -1, i_DatumRodjenja = -1; //indeksi imena, prezimena i datuma rodjenja

        String filePathString = "C:/Selekcijski zadatak_java backend_pomocni_resursi/csv_fajlovi/" +
                name + ".csv";

        BufferedReader br = new BufferedReader(new FileReader(filePathString)));

        while((line = br.readLine()) != null) {

            List<String> CSVData = Arrays.asList(line.split("\\s*,\\s*"));

        }

            int CSV_Size = CSVData.size();
            for(int i = 0; i < CSV_Size; i++) {
                if (CSVData.get(i).equalsIgnoreCase("ime")) {
                    i_Ime = i;
                }

                if (CSVData.get(i).equalsIgnoreCase("prezime")) {
                    i_Prezime = i;
                }

                if (CSVData.get(i).equalsIgnoreCase("datumRodjenja")) {
                    i_DatumRodjenja = i;
                }

                if(i_Ime > - 1 && i_Prezime > -1 && i_DatumRodjenja > -1) break;
            }




            for(int i = 3; i < CSV_Size; i++) {

                if(i % 3 == i_Ime) {

                }

            }




        osobaRepo.save();

        return new ResponseEntity<>(, HttpStatus.OK);

    }



    @GetMapping
    public void getOsobaById() {

    }



}
