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
    private ArtistDao ArtistDao;

    public List<Artist> findAll() {
        return ArtistDao.findAll();
    }

    public Artist findById(Long artistId) {
        return ArtistDao.findById(artistId).orElse(null);
    }

    public Artist save(Artist collection) {
        return ArtistDao.save(collection);
    }

    public void delete(Long artistId) {
    	ArtistDao.deleteById(artistId);
    }

	@Transactional(readOnly = true)
	public List<ArtistData> retrieveAllArtists() {
		List<Artist> artistEntities = ArtistDao.findAll();
		List<ArtistData> artistDtos = new LinkedList<>();
		
		for(Artist artist : artistEntities) {
			ArtistData artistData = new ArtistData(artist);
			artistDtos.add(artistData);
		}
		
		return artistDtos;
	}

	@Transactional(readOnly = false)
	public ArtistData saveArtist(ArtistData artistData) {
		Artist artist = artistData.toArtist();
		Artist dbArtist = ArtistDao.save(artist);
		
		return new ArtistData(dbArtist);
	}
}