package record.collection.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import record.collection.controller.model.ContributorData.GenreData;
import record.collection.dao.GenreDao;
import record.collection.entity.Album;
import record.collection.entity.Genre;

@Service
public class GenreService {
    @Autowired
    private GenreDao genreDao;
    @Autowired
    private AlbumService albumService;
    
    public List<Genre> findAll() {
        return genreDao.findAll();
    }

    public Genre findById(Long id) {
        return genreDao.findById(id).orElse(null);
    }

    public Genre save(Genre genre) {
        return genreDao.save(genre);
    }

    @Transactional(readOnly = true)
	public List<GenreData> retrieveAllGenres() {
    	List<Genre> genreEntities = genreDao.findAll();
    	List<GenreData> genreDtos = new LinkedList<>();
    	
    	for(Genre genre : genreEntities) {
    		GenreData genreData = new GenreData(genre);
    		genreDtos.add(genreData);
    	}
    	
		return genreDtos;
	}

	@Transactional(readOnly = false)
	public GenreData saveGenre(GenreData genreData) {
		Genre genre = genreData.toGenre();
		Genre dbGenre = genreDao.save(genre);
		
		return new GenreData(dbGenre);
	}
	@Transactional(readOnly = false)
	public void joinGenreAndAlbum(Long genreId, Long albumId) {
		Genre genre = findById(genreId);
		Album album = albumService.findById(albumId);
		album.getGenres().add(genre);
		genre.getAlbums().add(album);
	}
}