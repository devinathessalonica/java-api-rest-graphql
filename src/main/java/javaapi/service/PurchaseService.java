package javaapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaapi.dto.PurchaseDto;
import javaapi.model.Purchase;
import javaapi.repository.PurchaseRepository;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public List<PurchaseDto> getAllPurchases() {
        // List<Purchase> aa = purchaseRepository
        //         .findAll();
        //         System.out.print(aa);
       return ((List<Purchase>) purchaseRepository
                .findAll())
                .stream()
                .map(this::convertToPurchaseDTO)
                .collect(Collectors.toList());
	}

    private PurchaseDto convertToPurchaseDTO(Purchase purchase) { 
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
                PurchaseDto purchaseDto = modelMapper
                .map(purchase, PurchaseDto.class);	
        return purchaseDto;
    }
    // private PurchaseDto convertToPurchaseDTO(Purchase purchase) { 
    //     PurchaseDto purchaseDto = new PurchaseDto();
    //     purchaseDto.setSupplier(purchase.getSupplier());
    //     purchaseDto.setDates(purchase.getDates());
    //     purchaseDto.setTotal(purchase.getTotal());
    //     return purchaseDto;
    // }

    
}
