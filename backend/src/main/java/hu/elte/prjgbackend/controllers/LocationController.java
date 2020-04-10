package hu.elte.prjgbackend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;

import hu.elte.prjgbackend.models.Location;
import hu.elte.prjgbackend.repositories.LocationRepository;
import hu.elte.prjgbackend.services.LocationService;

@RestController
@RequestMapping("/locations")
public class LocationController{

    @Autowired
    private LocationRepository locationRepository;
    
    @Autowired
    private LocationService locationService;

    @GetMapping("/all")
    public List<Location> getLocations(){
        return locationService.findAll();
    }

    @PutMapping("newLocation")
    public ResponseEntity<Location> addNewLocation(@Valid @RequestBody Location location){
        locationRepository.save(location);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable Long id){
        Optional<Location> locationOpt = locationService.findLocationById(id);
        if(!locationOpt.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(locationOpt.get(), HttpStatus.OK);
    } 


}