package fpt.CapstoneSU24.controller;

import fpt.CapstoneSU24.model.Transport;
import fpt.CapstoneSU24.repository.TransportRepository;
import fpt.CapstoneSU24.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transport")
public class TransportController {

    private final TransportService transportService;
    @Autowired
    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }
    @GetMapping("/getAllTransport")
    public ResponseEntity<?> getAllTransport() {
        return transportService.getAllTransport();

    }
}
