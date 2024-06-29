package fpt.CapstoneSU24.service;

import fpt.CapstoneSU24.model.Transport;
import fpt.CapstoneSU24.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {

    private final TransportRepository transportRepository;
    @Autowired
    public TransportService(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    public ResponseEntity<?> getAllTransport() {
        List<Transport> transport = transportRepository.findAll();
        return ResponseEntity.ok(transport);
    }
}
