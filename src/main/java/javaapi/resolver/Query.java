package javaapi.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javaapi.model.Merk;
import javaapi.model.Barang;
import javaapi.repository.MerkRepository;
import javaapi.repository.BarangRepository;

import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {
  private MerkRepository merkRepository;
  private BarangRepository barangRepository;

  @Autowired
  public Query(MerkRepository merkRepository, BarangRepository barangRepository) {
    this.merkRepository = merkRepository;
    this.barangRepository = barangRepository;
  }

  public Iterable<Merk> findAllMerks() {
    return merkRepository.findAll();
  }

  public Iterable<Barang> findAllBarangs() {
    return barangRepository.findAll();
  }

  public Optional<Barang> barangsById(String id) {
    return barangRepository.findById(id);
  }

  public Optional<Merk> merksById(int id) {
    return merkRepository.findById(id);
  }

  public long countMerks() {
    return merkRepository.count();
  }

  public long countBarangs() {
    return barangRepository.count();
  }

}