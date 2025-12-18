package nl.novi.vinylshop.helpers;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.vinylshop.entities.GenreEntity;
import nl.novi.vinylshop.entities.PublisherEntity;
import nl.novi.vinylshop.repository.GenreRepository;
import nl.novi.vinylshop.repository.PublisherRepository;
import org.springframework.stereotype.Component;

@Component
public class ServiceHelper {
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;

    public ServiceHelper(GenreRepository genreRepository, PublisherRepository publisherRepository) {
        this.genreRepository = genreRepository;
        this.publisherRepository = publisherRepository;
    }

    public GenreEntity getGenreEntity(long genreId){
        return genreRepository.findById(genreId).orElseThrow(() -> new EntityNotFoundException("genre " + genreId + " niet gevonden"));
    }

    public PublisherEntity getPublisherEntity(long publisherId){
        return publisherRepository.findById(publisherId).orElseThrow(() -> new EntityNotFoundException("publisher " + publisherId + " niet gevonden"));
    }

}
