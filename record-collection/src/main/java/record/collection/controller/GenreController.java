package record.collection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping("/genre")
	public List<GenreData> retrieveAllGenres() {
		log.info("Retrieving all genres.");
		return genreService.retrieveAllGenres();
	}
}