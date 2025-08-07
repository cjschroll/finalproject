package record.collection.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import record.collection.entity.Contributor;

public interface ContributorDao extends JpaRepository<Contributor, Long> {

}
