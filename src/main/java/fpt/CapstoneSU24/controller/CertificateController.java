package fpt.CapstoneSU24.controller;

import fpt.CapstoneSU24.dto.Certificate.CreateCertificateRequest;
import fpt.CapstoneSU24.dto.payload.IdRequest;
import fpt.CapstoneSU24.service.CertificateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/certificate")
public class CertificateController {
    @Autowired
    CertificateService certificateService;
    @PostMapping("/getListCertificateByManuId")
    public ResponseEntity<?> getListCertificateByManuId(@Valid @RequestBody IdRequest req)
    {
     return certificateService.getListCertificateByManuId(req);
    }

    @GetMapping("/getListManuToVerify")
    public ResponseEntity<?> getListManuToVerify()
    {
        return certificateService.getListManuToVerify();
    }

    @PostMapping("/createCertificate")
    public ResponseEntity<?> createCertificate(@Valid @RequestBody CreateCertificateRequest req) throws IOException {
        return certificateService.createCertificate(req);
    }
}





