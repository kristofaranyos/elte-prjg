package hu.elte.prjgbackend.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hu.elte.prjgbackend.models.Location;
import hu.elte.prjgbackend.repositories.LocationRepository;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;
    
    public List<Location> findAll(){
    	return locationRepository.findAll();
    }
    
    public Optional<Location> findLocationById(Long id){
    	return locationRepository.findById(id);
    }
    
    public void addNewLocation(Location loc) {
    	locationRepository.save(loc);
    }
}
