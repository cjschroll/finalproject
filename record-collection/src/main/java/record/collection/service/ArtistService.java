package record.collection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import record.collection.dao.ArtistDao;
import record.collection.entity.Artist;

@Service
public class ArtistService {
    @Autowired
    private ArtistDao ArtistDao;

    public List<Artist> findAll() {
        return ArtistDao.findAll();
    }

    public Artist findById(Long id) {
        return ArtistDao.findById(id).orElse(null);
    }

    public Artist save(Artist collection) {
        return ArtistDao.save(collection);
    }

    public void delete(Long id) {
    	ArtistDao.deleteById(id);
    }
}