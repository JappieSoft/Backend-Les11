package nl.novi.vinylshop.dtos.response;

import java.util.List;

public class AlbumExtendedResponseDTO extends AlbumResponseDTO{
    private Long id;
    private String title;
    private int releaseYear;
    private GenreResponseDTO genre;
    private PublisherResponseDTO publisher;
    private List<ArtistResponseDTO> artists;
    private List<StockResponseDTO> stock;

    @Override
    public Long getId() {return id;}
    @Override
    public void setId(Long id) {this.id = id;}

    @Override
    public String getTitle() {return title;}
    @Override
    public void setTitle(String title) {this.title = title;}

    @Override
    public int getReleaseYear() {return releaseYear;}
    @Override
    public void setReleaseYear(int releaseYear) {this.releaseYear = releaseYear;}

    @Override
    public GenreResponseDTO getGenre() {return genre;}
    @Override
    public void setGenre(GenreResponseDTO genre) {this.genre = genre;}

    @Override
    public PublisherResponseDTO getPublisher() {return publisher;}
    @Override
    public void setPublisher(PublisherResponseDTO publisher) {this.publisher = publisher;}

    @Override
    public List<ArtistResponseDTO> getArtist() {return artists;}
    @Override
    public void setArtists(List<ArtistResponseDTO> artists) {this.artists = artists;}

    public List<StockResponseDTO> getStock() {return stock;}
    public void setStock(List<StockResponseDTO> stock) {this.stock = stock;}
}
