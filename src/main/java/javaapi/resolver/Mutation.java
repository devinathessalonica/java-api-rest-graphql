package javaapi.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javaapi.model.Merk;
import javaapi.model.Barang;
import javaapi.repository.MerkRepository;
import javaapi.repository.BarangRepository;

import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import javassist.NotFoundException;

@Component
public class Mutation implements GraphQLMutationResolver {
  private MerkRepository merkRepository;
  private BarangRepository barangRepository;

  @Autowired
  public Mutation(MerkRepository merkRepository, BarangRepository barangRepository) {
    this.merkRepository = merkRepository;
    this.barangRepository = barangRepository;
  }

  public Merk createMerk(String name) {
    Merk merk = new Merk();
    merk.setName(name);

    merkRepository.save(merk);

    return merk;
  }

  public Barang createBarang(String title, Integer merkId) {
    Barang barang = new Barang();
    barang.setMerk(new Merk(merkId));
    barang.setNama(title);

    barangRepository.save(barang);

    return barang;
  }

  public boolean deleteBarang(String id) {
    barangRepository.deleteById(id);
    return true;
  }

  public Barang updateBarang(String id, String title) throws NotFoundException {
    Optional<Barang> optBarang = barangRepository.findById(id);

    if (optBarang.isPresent()) {
      Barang barang = optBarang.get();

      if (title != null)
        barang.setNama(title);
      barangRepository.save(barang);
      return barang;
    }

    throw new NotFoundException("Not found Barang to update!");
  }

}
