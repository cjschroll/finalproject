package record.collection.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import record.collection.controller.model.ContributorData.ArtistData;
import record.collection.dao.ArtistDao;
import record.collection.entity.Artist;

@Service
public class ArtistService {
    @Autowired
    private ArtistDao artistDao;
    
    public List<Artist> findAll() {
        return artistDao.findAll();
    }

    public Artist findById(Long id) {
        return artistDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
	public List<ArtistData> retrieveAllArtists() {
    	List<Artist> artistEntities = artistDao.findAll();
    	List<ArtistData> artistDtos = new LinkedList<>();
    	
    	for(Artist artist : artistEntities) {
    		ArtistData artistData = new ArtistData(artist);
    		artistDtos.add(artistData);
    	}
		return artistDtos;
	}
}