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
import record.collection.controller.model.ContributorData.AlbumData;
import record.collection.service.AlbumService;

@RestController
@RequestMapping("/record_collection")
@Slf4j
public class AlbumController {

	@Autowired
	private AlbumService albumService;
	
	@PostMapping("/album")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AlbumData createAlbum(@RequestBody AlbumData albumData) {
		log.info("Creating album {}", albumData);
		return albumService.saveAlbum(albumData);
	}
	
	@GetMapping("/album")
	public List<AlbumData> retrieveAllAlbums() {
		log.info("Retrieving all albums.");
		return albumService.retrieveAllAlbums();
	}
}