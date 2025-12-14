package nl.novi.vinylshop.dtos.request;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AlbumRequestDTO {
    @Id
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100, message = "Title must be between 3 and 50 characters")
    private String title;

    @Min(1877)
    @Max(2100)
    private int releaseYear;
    private int genreId;
    private int publisherId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }

    public int getGenreId() {return genreId;}
    public void setGenreId(int genreId) {this.genreId = genreId;}

    public int getPublisherId() {return publisherId;}
    public void setPublisherId(int publisherId) {this.publisherId = publisherId;}
}
