package fpt.CapstoneSU24.service;

import fpt.CapstoneSU24.dto.TextDTO;
import fpt.CapstoneSU24.dto.payload.*;
import fpt.CapstoneSU24.mapper.LocationMapper;
import fpt.CapstoneSU24.model.Category;
import fpt.CapstoneSU24.model.Item;
import fpt.CapstoneSU24.model.Location;
import fpt.CapstoneSU24.model.User;
import fpt.CapstoneSU24.repository.CategoryRepository;
import fpt.CapstoneSU24.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Autowired
    public LocationService(LocationRepository locationRepository, LocationMapper locationMapper){
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;

    }
    public ResponseEntity<?> viewAllLocationByManufacturer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        List<Location> locations = locationRepository.findAllByPhone(currentUser.getPhone());
        if(locations.size() != 0){
            return ResponseEntity.status(HttpStatus.OK).body(locationMapper.locationToViewAllLocationDTOResponse(locations));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("don't have any location");
    }
    public ResponseEntity<?> getAllDistinctCity() {
        List<String> allLocation = locationRepository.findDistinctCities();
        List<TextDTO> locations = allLocation.stream()
                .map(city -> {
                    TextDTO dto = new TextDTO();
                    dto.setText(city);
                    return dto;
                })
                .collect(Collectors.toList());

        if (!locations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(locations);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Don't have any location");
    }
}