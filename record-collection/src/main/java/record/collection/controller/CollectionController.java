package record.collection.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import record.collection.controller.model.ContributorData;
import record.collection.entity.Contributor;
import record.collection.service.CollectionService;

@RestController
@RequestMapping("/record_collection")
@Slf4j
public class CollectionController {
	
	@Autowired
	private CollectionService collectionService;
	
	@PostMapping("/contributor")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ContributorData createContributor(@RequestBody ContributorData contributorData) {
		log.info("Creating contributor {}", contributorData);
		return collectionService.saveContributor(contributorData);
	}
	
	@PutMapping("/contributor/{contributorId}")
	public ContributorData updateContributor(@PathVariable Long contributorId, @RequestBody ContributorData contributorData) {
		contributorData.setContributorId(contributorId);
		log.info("Updating contributor {}", contributorData);
		return collectionService.saveContributor(contributorData);
	}
	
	@GetMapping("/contributor/{contributorId}")
	public Contributor findContributor(@PathVariable Long contributorId) {
		log.info("Retrieving contributor with ID={}", contributorId);
		return collectionService.findContributorById(contributorId);
	}
	
	@GetMapping("/contributor")
	public List<ContributorData> retrieveAllContributors() {
		log.info("Retrieving all contributors.");
		return collectionService.retrieveAllContributors();
	}
	
	@DeleteMapping("/contributor/{contributorId}")
	public Map<String, String> deleteContributor(@PathVariable Long contributorId) {
		log.info("Deleting contributor with ID=" + contributorId + " .");
		collectionService.deleteContributor(contributorId);
		
		return Map.of("message", "Contributor with ID=" + contributorId + " was deleted successfully.");
	}
	
	}
