package com.github.Selekcijski.zadatak_java.backend.repo;

import com.github.Selekcijski.zadatak_java.backend.Model.Osoba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OsobaRepo extends JpaRepository<Osoba, Long> {
}
