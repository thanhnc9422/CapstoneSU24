package fpt.CapstoneSU24.controller;

import fpt.CapstoneSU24.dto.SearchSupportSystemByUserDTO;
import fpt.CapstoneSU24.dto.SearchSupportSystemDTO;
import fpt.CapstoneSU24.dto.payload.AddSupportRequest;
import fpt.CapstoneSU24.dto.payload.ReplySupportRequest;
import fpt.CapstoneSU24.model.User;
import fpt.CapstoneSU24.service.SupportSystemService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/api/supportSystem")
@RestController
public class SupportSystemController {
    private final SupportSystemService supportSystemService;

    @Autowired
    public SupportSystemController(SupportSystemService supportSystemService) {
        this.supportSystemService = supportSystemService;
    }

    @PostMapping("/addSupport")
    public ResponseEntity<?> addSupport(@Valid @RequestBody AddSupportRequest req) throws IOException {
        return supportSystemService.addSupport(req);
    }

    @PostMapping("/replyBySupporter")
    public ResponseEntity<?> replyBySupporter(@Valid @RequestBody ReplySupportRequest req) throws IOException, MessagingException {
        return supportSystemService.replyBySupporter(req);
    }

    @PostMapping("/replyByUser")
    public ResponseEntity<?> replyByUser(@Valid @RequestBody ReplySupportRequest req) throws IOException {
        return supportSystemService.replyByUser(req);
    }

    @PostMapping("/listAllBySupporter")
    public ResponseEntity<?> listAllBySupporter(@Valid @RequestBody SearchSupportSystemDTO req) throws IOException {
        return supportSystemService.listAllBySupporter(req);
    }

    @PostMapping("/listAllByUser")
    public ResponseEntity<?> listAllByUser(@Valid @RequestBody SearchSupportSystemByUserDTO req) throws IOException {
        return supportSystemService.listAllByUser(req);
    }

    @GetMapping("/countStatus")
    public ResponseEntity<?> countStatus() throws IOException {
        return supportSystemService.countStatus();

    }
}
