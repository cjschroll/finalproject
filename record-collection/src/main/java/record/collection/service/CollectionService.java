package record.collection.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import record.collection.controller.model.ContributorData;
import record.collection.dao.ContributorDao;
import record.collection.entity.Contributor;

@Service
public class CollectionService {
	
	@Autowired
	private ContributorDao contributorDao;

	@Transactional(readOnly = false)
	public ContributorData saveContributor(ContributorData contributorData) {
		Contributor contributor = contributorData.toContributor();
		Contributor dbContributor = contributorDao.save(contributor);
		
		return new ContributorData(dbContributor);
	}
	
	@Transactional
	public ContributorData retrieveContributorById(Long contributorId) {
		Contributor contributor = findContributorById(contributorId);
		return new ContributorData(contributor);
	}
	
	@Transactional(readOnly = true)
	public ContributorData updateContributorById(Long contributorId) {
		Contributor contributor = findContributorById(contributorId);
		return new ContributorData(contributor);
	}

	public Contributor findContributorById(Long contributorId) {
		return contributorDao.findById(contributorId).orElseThrow(() -> new NoSuchElementException("Contributor with ID=" + contributorId + " was not found."));
	}

	@Transactional(readOnly = true)
	public List<ContributorData> retrieveAllContributors() {
		List<Contributor> contributorEntities = contributorDao.findAll();
		List<ContributorData> contributorDtos = new LinkedList<>();
		
		for(Contributor contributor : contributorEntities) {
			ContributorData contributorData = new ContributorData(contributor);
			contributorDtos.add(contributorData);
		}
		
		return contributorDtos;
	}

	@Transactional(readOnly = false)
	public void deleteContributor(Long contributorId) {
		Contributor contributor = findContributorById(contributorId);
		contributorDao.delete(contributor);
		
	}


}