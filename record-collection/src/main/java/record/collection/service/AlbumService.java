package record.collection.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import record.collection.controller.model.ContributorData.AlbumData;
import record.collection.dao.AlbumDao;
import record.collection.entity.Album;

@Service
public class AlbumService {
    @Autowired
    private AlbumDao albumDao;
    
    public List<Album> findAll() {
        return albumDao.findAll();
    }

    public Album findById(Long id) {
        return albumDao.findById(id).orElse(null);
    }

    public Album save(Album album) {
        return albumDao.save(album);
    }

    public void delete(Long id) {
        albumDao.deleteById(id);
    }
	
	@Transactional(readOnly = true)
	public List<AlbumData> retrieveAllAlbums() {
		List<Album> albumEntities = albumDao.findAll();
		List<AlbumData> albumDtos = new LinkedList<>();
		
		for(Album album : albumEntities) {
			AlbumData albumData = new AlbumData(album);
			albumDtos.add(albumData);
			}
		return albumDtos;
	}
}