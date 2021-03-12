package javaapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import javaapi.dto.PurchaseDto;
import javaapi.model.Purchase;
import javaapi.model.PurchaseDetail;
import javaapi.repository.BarangRepository;
import javaapi.repository.PurchaseRepository;
import javaapi.service.PurchaseService;
import javaapi.repository.PurchaseDetailRepository;
import javaapi.payload.request.PurchaseRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PurchaseController {
    private Logger LOG = LoggerFactory.getLogger(PurchaseController.class);

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    BarangRepository barangRepository;

    @Autowired
    PurchaseDetailRepository purchaseDetailRepository;

    @PostMapping(path = "/purchase", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseRequest purchaseRequest) {

        try {
            // System.out.println(purchaseRequest);
            Purchase purchase = new Purchase();
            purchase.setDates(purchaseRequest.getDate());
            purchase.setSupplier(purchaseRequest.getSupplier());
            // Optional<Barang> barangData = barangRepository.findById("aa3");
            // Barang brg = barangData.get();
            purchaseRepository.save(purchase);
            Double total = 0.0;
            List<PurchaseDetail> details = new ArrayList<>();
            for (PurchaseDetail result : purchaseRequest.getPurchaseDetail()) {
                total += result.getTotal() * result.getQty();
                PurchaseDetail detail = new PurchaseDetail();
                detail.setTotal(result.getTotal() * result.getQty());
                detail.setQty(result.getQty());
                detail.setPurchase(purchase.getId());
                detail.setBarang(result.getBarang());
                details.add(detail);
            }
            // System.out.println(details);
            purchaseDetailRepository.saveAll(details);
            purchase.setTotal(total);
            purchaseRepository.save(purchase);

            return new ResponseEntity<>(purchase, HttpStatus.CREATED);
        } catch (Exception e) {
            LOG.error(e.getMessage()
                    + "\r\n===================================================================================================");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/purchases")
    public ResponseEntity<List<PurchaseDto>> getAllPurchases(@RequestParam(required = false) String nama) {
        try {

            List<PurchaseDto> purchase = purchaseService.getAllPurchases();
            // System.out.println(purchase);
            // return purchase;
            return new ResponseEntity<>(purchase, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e.getMessage()
                    + "\r\n===================================================================================================");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @DeleteMapping("/purchase/{id}")
    public ResponseEntity<HttpStatus> deleteBarang(@PathVariable("id") Integer id) {
        try {
            purchaseDetailRepository.deleteByPurchase(id);
            purchaseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOG.error(e.getMessage()
                    + "\r\n===================================================================================================");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping("/purchase/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable("id") Integer id,
            @RequestBody PurchaseRequest purchaseRequest) {
        Optional<Purchase> purchaseData = purchaseRepository.findById(id);
        if (purchaseData.isPresent()) {
            Purchase purchase = purchaseData.get();
            purchase.setDates(purchaseRequest.getDate());
            purchase.setSupplier(purchaseRequest.getSupplier());
            purchaseRepository.save(purchase);

            purchaseDetailRepository.deleteByPurchase(id);

            Double total = 0.0;
            List<PurchaseDetail> details = new ArrayList<>();
            for (PurchaseDetail result : purchaseRequest.getPurchaseDetail()) {
                total += result.getTotal() * result.getQty();
                PurchaseDetail detail = new PurchaseDetail();
                detail.setTotal(result.getTotal() * result.getQty());
                detail.setQty(result.getQty());
                detail.setPurchase(purchase.getId());
                detail.setBarang(result.getBarang());
                details.add(detail);
            }
            // System.out.println(details);
            purchaseDetailRepository.saveAll(details);
            purchase.setTotal(total);
            purchaseRepository.save(purchase);

            return new ResponseEntity<>(purchase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
