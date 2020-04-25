package hu.elte.prjgbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.elte.prjgbackend.models.Location;

import hu.elte.prjgbackend.repositories.LocationRepository;

/**
 * Service for interacting with locations in the database;
 * @author Horváth Kristóf
 */


@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;
    
    /**
     * Returns all the locations stored in the database.
     * @return The list of the retrived locations.
     */
    public List<Location> findAll(){
    	return locationRepository.findAll();
    }
    
    /**
     * Returns one locations selected by the given id.
     * @param id The id of the location to be retrived.
     * @return The location which has the given id.
     */
    public Optional<Location> findLocationById(Long id){
    	return locationRepository.findById(id);
    }
    
    /**
     * Saving a new location to the database.
     * @param loc The locations what will be saved.
     */
    public void addNewLocation(Location loc) {
    	locationRepository.save(loc);
    }
}