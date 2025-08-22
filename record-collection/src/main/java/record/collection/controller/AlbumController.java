package record.collection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping("/album")
	public List<AlbumData> retrieveAllAlbums() {
		log.info("Retrieving all albums.");
		return albumService.retrieveAllAlbums();
	}
}