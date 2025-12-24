package nl.novi.vinylshop.helpers;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.vinylshop.entities.AlbumEntity;
import nl.novi.vinylshop.entities.GenreEntity;
import nl.novi.vinylshop.entities.PublisherEntity;
import nl.novi.vinylshop.repository.AlbumRepository;
import nl.novi.vinylshop.repository.GenreRepository;
import nl.novi.vinylshop.repository.PublisherRepository;
import org.springframework.stereotype.Component;

@Component
public class ServiceHelper {
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;
    private final AlbumRepository albumRepository;

    public ServiceHelper(GenreRepository genreRepository, PublisherRepository publisherRepository, AlbumRepository albumRepository) {
        this.genreRepository = genreRepository;
        this.publisherRepository = publisherRepository;
        this.albumRepository = albumRepository;
    }

    public GenreEntity getGenreEntity(long genreId){
        return genreRepository.findById(genreId).orElseThrow(() -> new EntityNotFoundException("genre " + genreId + " niet gevonden"));
    }

    public PublisherEntity getPublisherEntity(long publisherId){
        return publisherRepository.findById(publisherId).orElseThrow(() -> new EntityNotFoundException("publisher " + publisherId + " niet gevonden"));
    }

    public AlbumEntity getAlbumEntity(long albumId){
        return albumRepository.findById(albumId).orElseThrow(() -> new EntityNotFoundException("album " + albumId + " niet gevonden"));
    }
}
