package record.collection.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import record.collection.entity.Genre;

public interface GenreDao extends JpaRepository<Genre, Long> {

}
