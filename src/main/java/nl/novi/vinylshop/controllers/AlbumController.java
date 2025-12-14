package nl.novi.vinylshop.controllers;

import jakarta.validation.Valid;
import nl.novi.vinylshop.dtos.request.AlbumRequestDTO;
import nl.novi.vinylshop.dtos.response.AlbumResponseDTO;
import nl.novi.vinylshop.helpers.UrlHelper;
import nl.novi.vinylshop.services.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final UrlHelper urlHelper;

    public AlbumController(AlbumService albumService, UrlHelper urlHelper) {
        this.albumService = albumService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<AlbumResponseDTO>> getAllAlbums() {
        List<AlbumResponseDTO> albums = albumService.findAllAlbums();
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<AlbumResponseDTO> getAlbumById(@PathVariable Long albumId) {
        AlbumResponseDTO album = albumService.findAlbumById(albumId);
        if (album == null) {
            return ResponseEntity.notFound().build(); // check of genre bestaat anders hier dus 404 Not Found
        }
        return new ResponseEntity<AlbumResponseDTO>(album, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlbumResponseDTO> createAlbum(@Valid @RequestBody AlbumRequestDTO albumInput) {
        AlbumResponseDTO newAlbum = albumService.createAlbum(albumInput);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newAlbum.getId())).body(newAlbum);
    }

    @PutMapping("/{albumId}")
    public ResponseEntity<AlbumResponseDTO> updateAlbum(@PathVariable Long albumId, @Valid @RequestBody AlbumRequestDTO albumInput) {
        AlbumResponseDTO updatedAlbum = albumService.updateAlbum(albumId, albumInput);
        return new ResponseEntity<>(updatedAlbum, HttpStatus.OK);
    }

    @DeleteMapping("/{albumId}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long albumId) {
        albumService.deleteAlbum(albumId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}