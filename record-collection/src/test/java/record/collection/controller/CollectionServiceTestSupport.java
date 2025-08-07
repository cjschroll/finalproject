package record.collection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import record.collection.controller.model.ContributorData;
import record.collection.entity.Contributor;

public class CollectionServiceTestSupport {

	private static final String ALBUM_TABLE = "album";

	private static final String ALBUM_GENRE_TABLE = "album_genre";

	private static final String GENRE_TABLE = "genre";

	private static final String CONTRIBUTOR_TABLE = "contributor";

	private static final String INSERT_ALBUM_1_SQL = """
			INSERT INTO album
			(album_title, year_released, contributor_id)
			VALUES ('Three Cheers for Sweet Revenge', '2003', 1);
			""";

	private static final String INSERT_ALBUM_2_SQL = """
			INSERT INTO album
			(album_title, year_released, contributor_id)
			VALUES ('From Underneath the Cork Tree', '2004', 2);
			""";

	private static final String INSERT_GENRES_1_SQL = """
			INSERT INTO genre_album
			(album_id, genre_id, album_title)
			VALUES (1, 3, 'Three Cheers for Sweet Revenge');
			""";

	private static final String INSERT_GENRES_2_SQL = """
			INSERT INTO genre_album
			(album_id, genre_id, album_title)
			VALUES (2, 1, 'From Underneath the Cork Tree');
			""";

	// @formatter:off
	private ContributorData insertContributor1 = new ContributorData(
			1L,
			"Gerard Way",
			"mcr_black parade"
	);
	
	private ContributorData insertContributor2 = new ContributorData(
			2L,
			"Pete Wentz",
			"going_down_swinging"
			
	);
	
	private ContributorData insertUsername1 = new ContributorData(
			1L,
			"Adam Lazzara",
			"breakyoudownsobadly"
	);
	
	private ContributorData insertUsername2 = new ContributorData(
			1L,
			"Adam Lazzara",
			"breakyoudownsobadly"
	);
	
	private ContributorData updateUsername1 = new ContributorData(
			1L,
			"Gerard Way",
			"GrandMarshallOfTheBlackParade"
	);
	
	
	//formatter:on
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CollectionController collectionController;
	
	protected ContributorData buildInsertContributor(int which) {
		return which == 1 ? insertContributor1 : insertContributor2;
	}
	protected int rowsInContributorTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, CONTRIBUTOR_TABLE);
	}

	protected ContributorData insertContributor(ContributorData contributorData) {
		Contributor contributor = contributorData.toContributor();
		ContributorData clone = new ContributorData(contributor);
		
		clone.setContributorId(null);
		return collectionController.createContributor(clone);
	}

	protected Contributor retrieveContributor(Long contributorId) {
		return collectionController.findContributor(contributorId);
	}
	
	protected List<ContributorData> insertTwoContributors() {
		ContributorData contributor1 = insertContributor(buildInsertContributor(1));
		ContributorData contributor2 = insertContributor(buildInsertContributor(2));
		
		return List.of(contributor1, contributor2);
	}
	
	protected List<ContributorData> retrieveAllContributors() {
		return collectionController.retrieveAllContributors();
	}
	
	protected ContributorData updatedContributor(ContributorData contributorData) {
		return collectionController.updateContributor(contributorData.getContributorId(), contributorData);
	}

	protected ContributorData buildUpdateContributor() {
		return updateUsername1;
	}
	
	protected void insertAlbum(int which) {
		String albumSql = which == 1 ? INSERT_ALBUM_1_SQL : INSERT_ALBUM_2_SQL;
		String genreSql = which == 1 ? INSERT_GENRES_1_SQL : INSERT_GENRES_2_SQL;
		
		jdbcTemplate.update(albumSql);
		jdbcTemplate.update(genreSql);
	}
	
	protected ContributorData insertLocation(ContributorData contributorData) {
		Contributor contributor = contributorData.toContributor();
		ContributorData clone = new ContributorData(contributor);
		
		clone.setContributorId(null);
		return collectionController.createContributor(clone);
	}
	
	protected ContributorData buildInsertContributor1(int which) {
		return which == 1 ? insertUsername1 : insertUsername2;
	}

	protected int rowsInGenreTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, GENRE_TABLE);
	}
	
	protected int rowsInAlbumGenreTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, ALBUM_GENRE_TABLE);
	}

	protected int rowsInDogTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, ALBUM_TABLE);
		}
	
	protected void deleteContributor(Long contributorId) {
		collectionController.deleteContributor(contributorId);
	}
	
	}