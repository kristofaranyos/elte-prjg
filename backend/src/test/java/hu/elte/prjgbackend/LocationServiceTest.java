package hu.elte.prjgbackend;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.hamcrest.MatcherAssert;

import hu.elte.prjgbackend.models.Location;
import hu.elte.prjgbackend.services.LocationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationServiceTest {
	
	@MockBean
	LocationService locationService;

	@Test
	public void testFindAllListIsEmpty(){
		Mockito.when(locationService.findAll()).thenReturn(new ArrayList<Location>());
		
		ArrayList<Location> locs = (ArrayList<Location>) locationService.findAll();
		MatcherAssert.assertThat("", locs.isEmpty());
	}
	
	@Test
	public void testFindAllListContainsElements(){
		ArrayList<Location> mockLocs = new ArrayList<Location>();
		mockLocs.add(new Location(0L, "Location", "Address", 21.432, 23.4232, "Description"));
		mockLocs.add(new Location(1L, "Location2", "Address2", 21.431, 23765, "Description2"));
		
		Mockito.when(locationService.findAll()).thenReturn(mockLocs);
		
		ArrayList<Location> locs = (ArrayList<Location>) locationService.findAll();
		MatcherAssert.assertThat("", locs.equals(mockLocs));
	}
	
	@Test
	public void testFindByIdFound() {
		ArrayList<Location> mockLocs = new ArrayList<Location>();
		mockLocs.add(new Location(0L, "Location", "Address", 21.432, 23.4232, "Description"));
		mockLocs.add(new Location(1L, "Location2", "Address2", 21.431, 23765, "Description2"));
		Mockito.when(locationService.findLocationById(1L)).thenReturn(Optional.ofNullable(findById(1L, mockLocs)));
		
		
		MatcherAssert.assertThat("", locationService.findLocationById(1L).isPresent());
	}
	
	private Location findById(Long id, ArrayList<Location> locs) {
		for(Location loc: locs) {
			if(loc.getId()==id) {
				return loc;
			}
		}
		return null;
	}
	
	@Test
	public void testFindByIdNotFound() {
		ArrayList<Location> mockLocs = new ArrayList<Location>();
		mockLocs.add(new Location(0L, "Location", "Address", 21.432, 23.4232, "Description"));
		mockLocs.add(new Location(1L, "Location2", "Address2", 21.431, 23765, "Description2"));
		Mockito.when(locationService.findLocationById(3L)).thenReturn(Optional.ofNullable(findById(3L, mockLocs)));
		
		
		MatcherAssert.assertThat("", !locationService.findLocationById(1L).isPresent());
	}
}
