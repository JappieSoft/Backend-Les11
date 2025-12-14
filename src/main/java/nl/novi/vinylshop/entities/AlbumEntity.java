package nl.novi.vinylshop.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class AlbumEntity extends BaseEntity {

    @Column(name ="title")
    private String title;

    @Column(name ="release_year")
    private int releaseYear;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private PublisherEntity publisher;

    @OneToMany(mappedBy = "album")
    private Set<StockEntity> stockItems = new HashSet<>();

    @ManyToMany(mappedBy = "albums")
    private Set<ArtistEntity> artists = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    public String getTitle() { return title; }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}