package record.collection.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.transaction.annotation.Transactional;

import record.collection.RecordCollectionApplication;
import record.collection.controller.model.ContributorData;

@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes =  RecordCollectionApplication.class)

@ActiveProfiles("test")

@Sql(scripts = {"classpath:schema.sql"})
@SqlConfig(encoding = "utf-8")
class CollectionControllerTest extends CollectionServiceTestSupport {

	@Test
	@Transactional
	void testInsertContributor() {
		ContributorData request = buildInsertContributor(1);
		ContributorData expected = buildInsertContributor(1);
		
		ContributorData actual = insertContributor(request);
		
		assertThat(actual).isEqualTo(expected);
		
		assertThat(rowsInContributorTable()).isOne();
	}
	
	@Test
	@Transactional
	void testRetrieveAllContributors() {
	    List<ContributorData> expected = insertTwoContributors();
	    List<ContributorData> actual = retrieveAllContributors();
		
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	@Transactional
	void testUpdateContributor() {
		insertContributor(buildInsertContributor(1));
		ContributorData expected = buildUpdateContributor();
		ContributorData actual = updatedContributor(expected);
		assertThat(actual).isEqualTo(expected);
		assertThat(rowsInContributorTable()).isOne();
	}

	@Test
	void testDeleteContributorWithAlbums() {
		ContributorData contributor = insertLocation(buildInsertContributor(1));
		Long contributorId = contributor.getContributorId();
		
		insertAlbum(1);
		insertAlbum(2);
		
		assertThat(rowsInContributorTable()).isOne();
		assertThat(rowsInDogTable()).isEqualTo(2);
		assertThat(rowsInAlbumGenreTable()).isEqualTo(4);
		int genreRows = rowsInGenreTable();
		
		deleteContributor(contributorId);
		
		assertThat(rowsInContributorTable()).isZero();
		assertThat(rowsInDogTable()).isZero();
		assertThat(rowsInGenreTable()).isZero();
		
		assertThat(rowsInGenreTable()).isEqualTo(genreRows);
	}

	protected void deleteContributor(Long contributorId) {
		
	}

	protected int rowsInAlbumGenreTable() {
		return 0;
	}

	protected int rowsInGenreTable() {
		return 0;
	}

	protected int rowsInDogTable() {
		return 0;
	}

	
	
}