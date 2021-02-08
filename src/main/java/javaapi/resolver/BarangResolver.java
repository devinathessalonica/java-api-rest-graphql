package javaapi.resolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javaapi.model.Merk;
import javaapi.model.Barang;
import javaapi.repository.MerkRepository;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.security.access.prepost.PreAuthorize;

@Component
@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
public class BarangResolver implements GraphQLResolver<Barang> {
  @Autowired
  private MerkRepository merkRepository;

  public BarangResolver(MerkRepository merkRepository) {
    this.merkRepository = merkRepository;
  }

  public Merk getMerk(Barang barang) {
    return merkRepository.findById(barang.getMerk().getId()).orElseThrow(null);
  }
}