package javaapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import javaapi.model.Barang;

public interface BarangRepository extends JpaRepository<Barang, String> {
        List<Barang> findByStatus(int status);
        Optional<Barang> findById(String id);
        List<Barang> findByNamaContaining(String nama);
      }

