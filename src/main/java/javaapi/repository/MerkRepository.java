package javaapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import javaapi.model.Merk;

public interface MerkRepository extends JpaRepository<Merk, Integer> {
    Optional<Merk> findById(Integer id);

}