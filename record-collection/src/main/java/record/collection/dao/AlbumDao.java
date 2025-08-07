package record.collection.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import record.collection.entity.Album;

public interface AlbumDao extends JpaRepository<Album, Long> {

}
