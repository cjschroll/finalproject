package record.collection.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import record.collection.entity.Album;
import record.collection.entity.Artist;
import record.collection.entity.Contributor;
import record.collection.entity.Genre;

@Data
@NoArgsConstructor
public class ContributorData {
    private Long contributorId;
    private String contributorName;
    private String contributorUsername;
    private Set<AlbumData> albums = new HashSet<>();

    public ContributorData(Contributor contributor) {
        this.contributorId = contributor.getContributorId();
        this.contributorName = contributor.getContributorName();
        this.contributorUsername = contributor.getContributorUsername();
        
        for (Album album : contributor.getAlbums()) {
            this.albums.add(new AlbumData(album));
        }
    }

    public ContributorData(Long contributorId, String contributorName, String contributorUsername) {
        this.contributorId = contributorId;
        this.contributorName = contributorName;
        this.contributorUsername = contributorUsername;
    }

    public Contributor toContributor() {
        Contributor contributor = new Contributor();
        
        contributor.setContributorId(contributorId);
        contributor.setContributorName(contributorName);
        contributor.setContributorUsername(contributorUsername);
        
        for (AlbumData albumData : albums) {
            contributor.getAlbums().add(albumData.toAlbum());
        }
        
        return contributor;
    }

    @Data
    @NoArgsConstructor
    public static class AlbumData {
        private Long albumId;
        private String albumTitle;
        private int yearReleased;
        private Artist artist;
       // private Contributor contributor;
        private Set<GenreData> genres = new HashSet<>();
        
        public AlbumData(Album album) {
            this.albumId = album.getAlbumId();
            this.albumTitle = album.getAlbumTitle();
            this.yearReleased = album.getYearReleased();
            this.artist = album.getArtist();
           // this.contributor = album.getContributor();
            
            for (Genre genre : album.getGenres()) {
                this.genres.add(new GenreData(genre));
            }
        }
        
        public Album toAlbum() {
            Album album = new Album();
            album.setAlbumId(albumId);
            album.setAlbumTitle(albumTitle);
            album.setYearReleased(yearReleased);
            //album.setArtist(artist);
            //album.setContributor(contributor);
            
           //Set<Genre> genreList = new HashSet<>();
            for (GenreData genreData : genres) {
                album.getGenres().add(genreData.toGenre());
            }
           //album.setGenres(genreList);
            
            return album;
        }
    }

    @Data
    @NoArgsConstructor
    public static class GenreData {
        private Long genreId;
        private String genreName;
        
        public GenreData(Genre genre) {
            this.genreId = genre.getGenreId();
            this.genreName = genre.getGenreName();
        }
        
        public Genre toGenre() {
            Genre genre = new Genre();
            genre.setGenreId(genreId);
            genre.setGenreName(genreName);
            return genre;
        }
    }
        
        @Data
        @NoArgsConstructor
        public static class ArtistData {
            private Long artistId;
            private String artistName;
            
            public ArtistData(Artist artist) {
                this.artistId = artist.getArtistId();
                this.artistName = artist.getArtistName();
            }
            
            public Artist toArtist() {
                Artist artist = new Artist();
                artist.setArtistId(artistId);
                artist.setArtistName(artistName);
                return artist;
            }
    }
}