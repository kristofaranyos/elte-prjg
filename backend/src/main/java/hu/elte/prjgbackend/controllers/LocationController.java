package hu.elte.prjgbackend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.elte.prjgbackend.models.Location;

@RestController
@RequestMapping("/locations")
public class LocationController{

    @GetMapping("/all")
    public List<Location> getLocations(){
        ArrayList<Location> locations = new ArrayList<>();
        Location l1 = new Location();
        l1.setAddress("Budapest, Szent István tér 1, 1051");
        l1.setLocationName("Szent István Bazilika");
        l1.setLongitude(47.500810);
        l1.setLatitude(19.054021);
        l1.setDescription("Szép nagy templom.");
        locations.add(l1);
        return locations;
    }

}