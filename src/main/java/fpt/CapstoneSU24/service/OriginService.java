package fpt.CapstoneSU24.service;

import fpt.CapstoneSU24.model.Origin;
import fpt.CapstoneSU24.repository.OriginRepository;
import fpt.CapstoneSU24.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginService {

    private final OriginRepository originRepository;

    @Autowired
    public OriginService(OriginRepository originRepository, ProductRepository productRepository) {
        this.originRepository = originRepository;
    }

    public ResponseEntity<?> findAll() {
        List<Origin> originList = originRepository.findAll();
        return ResponseEntity.ok(originList);
    }
}
