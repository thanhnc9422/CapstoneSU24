package fpt.CapstoneSU24.controller;


import fpt.CapstoneSU24.dto.payload.VerifyAddressRequest;
import fpt.CapstoneSU24.dto.payload.VerifyDDAddressRequest;
import fpt.CapstoneSU24.service.OpenCageDataService;
import jakarta.validation.Valid;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final OpenCageDataService openCageDataService;

    @Autowired
    public LocationController(OpenCageDataService openCageDataService){
        this.openCageDataService = openCageDataService;
    }

    @Value("${key.open.cage.data}")
    private String apiKey;
    @Value("${url.open.cage.data}")
    private String urlVerify;

    @PostMapping("/verifyLocation")
    public ResponseEntity<?> verifyLocation(@Valid @RequestBody VerifyAddressRequest req) {
        JSONArray result = openCageDataService.verifyLocation(req.getAddress());
           if(result.isEmpty()){
               return ResponseEntity.status(200).body("The location not exist");
           }else{
               return ResponseEntity.status(200).body(result.toString());
           }

    }
    @PostMapping("/verifyCoordinates")
    public ResponseEntity<?> verifyCoordinates(@Valid @RequestBody VerifyDDAddressRequest req) {
            JSONArray result = openCageDataService.verifyCoordinates(req.getLat(), req.getLng());
            if(result.isEmpty()){
                return ResponseEntity.status(200).body("The location not exist");
            }else{
                return ResponseEntity.status(200).body(result.toString());
            }
    }
}
