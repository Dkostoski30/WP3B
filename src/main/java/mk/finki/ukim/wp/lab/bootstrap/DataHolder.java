package mk.finki.ukim.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.repository.jpa.AlbumRepository;
import mk.finki.ukim.wp.lab.repository.jpa.ArtistRepository;
import mk.finki.ukim.wp.lab.repository.jpa.SongRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class DataHolder implements CommandLineRunner{

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;

    public DataHolder(SongRepository songRepository, ArtistRepository artistRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();

        // Generate random artists
        List<Artist> artists = List.of(
                new Artist("John", "Lennon", "Member of The Beatles"),
                new Artist("Paul", "McCartney", "Member of The Beatles"),
                new Artist("Freddie", "Mercury", "Lead singer of Queen"),
                new Artist("Elton", "John", "British singer-songwriter"),
                new Artist("David", "Bowie", "English singer-songwriter"),
                new Artist("Whitney", "Houston", "American singer and actress"),
                new Artist("Michael", "Jackson", "The King of Pop"),
                new Artist("Adele", "Adkins", "British singer-songwriter"),
                new Artist("Beyoncé", "Knowles", "American singer and actress"),
                new Artist("Ed", "Sheeran", "British singer-songwriter")
        );
        artistRepository.saveAll(artists);

        // Generate random albums
        List<Album> albums = List.of(
                new Album("Abbey Road", "Rock", "1969"),
                new Album("A Night at the Opera", "Rock", "1975"),
                new Album("Thriller", "Pop", "1982"),
                new Album("Back to Black", "Soul", "2006"),
                new Album("21", "Pop", "2011")
        );
        albumRepository.saveAll(albums);

        // Generate random songs
        List<Song> songs = List.of(
                new Song("Come Together", "Rock", 1969, List.of(artists.get(0), artists.get(1))),
                new Song("Bohemian Rhapsody", "Rock", 1975, List.of(artists.get(2))),
                new Song("Billie Jean", "Pop", 1982, List.of(artists.get(6))),
                new Song("Rolling in the Deep", "Pop", 2011, List.of(artists.get(7))),
                new Song("Halo", "Pop", 2008, List.of(artists.get(8))),
                new Song("Shape of You", "Pop", 2017, List.of(artists.get(9))),
                new Song("Rocket Man", "Pop", 1972, List.of(artists.get(3))),
                new Song("Heroes", "Rock", 1977, List.of(artists.get(4))),
                new Song("I Will Always Love You", "Soul", 1992, List.of(artists.get(5))),
                new Song("Hey Jude", "Rock", 1968, List.of(artists.get(0), artists.get(1)))
        );
        songs.forEach(song -> song.setAlbum(albums.get(random.nextInt(albums.size()))));
        songRepository.saveAll(songs);
    }

}
