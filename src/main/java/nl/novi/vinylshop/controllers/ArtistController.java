package nl.novi.vinylshop.controllers;

import jakarta.validation.Valid;
import nl.novi.vinylshop.dtos.request.ArtistRequestDTO;
import nl.novi.vinylshop.dtos.response.ArtistResponseDTO;
import nl.novi.vinylshop.helpers.UrlHelper;
import nl.novi.vinylshop.services.AlbumService;
import nl.novi.vinylshop.services.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final AlbumService albumService;
    private final ArtistService artistService;
    private final UrlHelper urlHelper;

    public ArtistController(AlbumService albumService, ArtistService artistService, UrlHelper urlHelper) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<ArtistResponseDTO>> getAllArtist() {
        List<ArtistResponseDTO> artists = artistService.findAllArtists();
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> getArtistById(@PathVariable Long id) {
        ArtistResponseDTO artist = artistService.findArtistById(id);
        if (artist == null) {
            return ResponseEntity.notFound().build(); // check of genre bestaat anders hier dus 404 Not Found
        }
        return new ResponseEntity<ArtistResponseDTO>(artist, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ArtistResponseDTO> createArtist(@Valid @RequestBody ArtistRequestDTO artistInput) {
        ArtistResponseDTO newArtist = artistService.createArtist(artistInput);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newArtist.getId())).body(newArtist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> updateArtist(@PathVariable Long id, @Valid @RequestBody ArtistRequestDTO artistInput) {
        ArtistResponseDTO updatedArtist = artistService.updateArtist(id, artistInput);
        return new ResponseEntity<>(updatedArtist, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{albumId}/artists/{artistId}")
    public ResponseEntity<Void> linkArtist(@PathVariable Long albumId, @PathVariable Long artistId) {
        albumService.linkArtist(albumId, artistId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{albumId}/artists/{artistId}")
    public ResponseEntity<Void> unlinkArtist(@PathVariable Long albumId, @PathVariable Long artistId) {
        albumService.unlinkArtist(albumId, artistId);
        return ResponseEntity.ok().build();
    }

}
