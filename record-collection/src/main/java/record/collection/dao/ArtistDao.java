package record.collection.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import record.collection.entity.Artist;

public interface ArtistDao extends JpaRepository<Artist, Long> {

}
