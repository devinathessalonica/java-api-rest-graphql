package javaapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import javaapi.model.Barang;
import javaapi.repository.BarangRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class BarangController {

  @Autowired
  BarangRepository barangRepository;


  @GetMapping("/barangs")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public ResponseEntity<List<Barang>> getAllBarangs(@RequestParam(required = false) String nama) {
    try {
      List<Barang> barangs = new ArrayList<Barang>();

      if (nama == null)
        barangRepository.findAll().forEach(barangs::add);
      else
        barangRepository.findByNamaContaining(nama).forEach(barangs::add);

      if (barangs.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(barangs, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/barangs/{id}")
  public ResponseEntity<Barang> getBarangById(@PathVariable("id") String id) {
    Optional<Barang> barangData = barangRepository.findById(id);

    if (barangData.isPresent()) {
      return new ResponseEntity<>(barangData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/barangs")
  public ResponseEntity<Barang> createBarang(@RequestBody Barang barang) {
    try {
      Barang _barang = barangRepository
          .save(new Barang(barang.getId(),barang.getNama(), barang.getStatus(), barang.getMerk()));
      return new ResponseEntity<>(_barang, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/barangs/{id}")
  public ResponseEntity<Barang> updateBarang(@PathVariable("id") String id, @RequestBody Barang barang) {
    Optional<Barang> barangData = barangRepository.findById(id);
    // Logger.getLogger(MyLogger.class.getName());
    if (barangData.isPresent()) {
      Barang _barang = barangData.get();
      _barang.setNama(barang.getNama());
      _barang.setStatus(barang.getStatus());
      return new ResponseEntity<>(barangRepository.save(_barang), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/barangs/{id}")
  public ResponseEntity<HttpStatus> deleteBarang(@PathVariable("id") String id) {
    try {
      barangRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/barangs")
  public ResponseEntity<HttpStatus> deleteAllBarangs() {
    try {
      barangRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/barangs/published")
  public ResponseEntity<List<Barang>> findByStatus() {
    try {
      List<Barang> barangs = barangRepository.findByStatus(1);

      if (barangs.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(barangs, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
