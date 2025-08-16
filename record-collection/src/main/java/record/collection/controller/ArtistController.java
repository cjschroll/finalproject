package record.collection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import record.collection.controller.model.ContributorData.ArtistData;
import record.collection.service.ArtistService;

@RestController
@RequestMapping("/record_collection")
@Slf4j
public class ArtistController {

	@Autowired
	private ArtistService artistService;

	@PostMapping("/artist")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ArtistData createArtist(@RequestBody ArtistData artistData) {
		log.info("Creating artist {}", artistData);
		return artistService.saveArtist(artistData);
	}
	
	@GetMapping("/artist")
	public List<ArtistData> retrieveAllArtists() {
		log.info("Retrieving all artists.");
		return artistService.retrieveAllArtists();
	}
}
