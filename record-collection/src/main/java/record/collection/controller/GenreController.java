package record.collection.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import record.collection.controller.model.ContributorData.GenreData;
import record.collection.service.GenreService;

@RestController
@RequestMapping("/record_collection")
@Slf4j
public class GenreController {
		
	@Autowired
	private GenreService genreService;

	@PostMapping("/genre")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GenreData createGenre(@RequestBody GenreData genreData) {
		log.info("Creating genre {}", genreData);
		return genreService.saveGenre(genreData);
	}
	
	@GetMapping("/genre")
	public List<GenreData> retrieveAllGenres() {
		log.info("Retrieving all genres.");
		return genreService.retrieveAllGenres();
	}
	
	@PostMapping("/genre/{genreId}/{albumId}")
	public Map<String, String> joiningGenreToAlbum(@PathVariable Long genreId, @PathVariable Long albumId) {
		log.info("Adding genre with ID = {} to albume with ID = {}", genreId, albumId);
		genreService.joinGenreAndAlbum(genreId, albumId);
		
		return Map.of("message", "Genre with ID = " + genreId + "was added to Album with ID = " + albumId);
	}
}