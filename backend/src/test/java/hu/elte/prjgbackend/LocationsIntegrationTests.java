package hu.elte.prjgbackend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import hu.elte.prjgbackend.models.Location;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LocationsIntegrationTests {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
	public void testAddLocation() throws Exception {
		Location testLocation = new Location(1L, "Location2", "Address2", 21.431, 23765, "Description2");
		ObjectMapper mapper = new ObjectMapper();
		String testLocJson = mapper.writeValueAsString(testLocation);
        this.mockMvc.perform(put("/locations/newLocation")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(testLocJson)).andExpect(status().isOk()).andExpect(content().json(testLocJson));
	}
	
	@Test
	public void testAddLocationFail() throws Exception {
		Location testLocation = new Location(1L, "Location2", "Address2", 21.431, 23765, "Description2");
		ObjectMapper mapper = new ObjectMapper();
		String testLocJson = mapper.writeValueAsString(testLocation);
        this.mockMvc.perform(put("/locations/newLocation")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content("{fds"+testLocJson)).andExpect(status().is4xxClientError());
	}
	
	@Test
	public void testFindAllEmpty() throws Exception {
        this.mockMvc.perform(get("/locations/all")).andExpect(status().is2xxSuccessful())
        .andExpect(content().json("[]"));
	}
	
	@Test
	public void testFindAllOk() throws Exception {
		Location testLocation = new Location(1L, "Location2", "Address2", 21.431, 23765, "Description2");
		ObjectMapper mapper = new ObjectMapper();
		String testLocJson = mapper.writeValueAsString(testLocation);
		this.mockMvc.perform(put("/locations/newLocation")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(testLocJson));
        this.mockMvc.perform(get("/locations/all")).andExpect(status().is2xxSuccessful())
        .andExpect(content().json("["+testLocJson+"]"));
	}
}
