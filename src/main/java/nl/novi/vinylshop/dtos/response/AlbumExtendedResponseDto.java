package nl.novi.vinylshop.dtos.response;

import java.util.List;

public class AlbumExtendedResponseDto extends AlbumResponseDTO{
    private Long id;
    private String title;
    private int releaseYear;
    private GenreResponseDTO genre;
    private PublisherResponseDTO publisher;
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

    public GenreResponseDTO getGenre() {return genre;}
    public void setGenre(GenreResponseDTO genre) {this.genre = genre;}

    public PublisherResponseDTO getPublisher() {return publisher;}
    public void setPublisher(PublisherResponseDTO publisher) {this.publisher = publisher;}

    public List<StockResponseDTO> getStock() {return stock;}
    public void setStock(List<StockResponseDTO> stock) {this.stock = stock;}
}
