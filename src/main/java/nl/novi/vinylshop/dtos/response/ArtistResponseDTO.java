package nl.novi.vinylshop.dtos.response;

import java.util.List;

public class ArtistResponseDTO {
    private Long id;
    private String name;
    private String biography;
    private List<AlbumResponseDTO> albums;

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }
}
