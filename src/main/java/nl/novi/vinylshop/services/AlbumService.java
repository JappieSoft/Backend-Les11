package nl.novi.vinylshop.services;

import jakarta.transaction.Transactional;
import nl.novi.vinylshop.dtos.mapper.AlbumMapper;
import nl.novi.vinylshop.dtos.request.AlbumRequestDTO;
import nl.novi.vinylshop.dtos.response.AlbumResponseDTO;
import nl.novi.vinylshop.entities.AlbumEntity;
import nl.novi.vinylshop.entities.ArtistEntity;
import nl.novi.vinylshop.entities.GenreEntity;
import nl.novi.vinylshop.entities.PublisherEntity;
import nl.novi.vinylshop.helpers.ServiceHelper;
import nl.novi.vinylshop.repository.AlbumRepository;
import nl.novi.vinylshop.repository.ArtistRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private final AlbumMapper albumMapper;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final ServiceHelper serviceHelper;

    public AlbumService(AlbumMapper albumMapper, AlbumRepository albumRepository, ArtistRepository artistRepository ,ServiceHelper serviceHelper) {
        this.albumMapper = albumMapper;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.serviceHelper = serviceHelper;
    }

    @Transactional
    public List<AlbumResponseDTO> findAllAlbums() {
        return albumMapper.mapToDto(albumRepository.findAll());
    }

    @Transactional
    public AlbumResponseDTO findAlbumById(Long id) {
        Optional<AlbumEntity> AlbumEntity = albumRepository.findById(id);
        if (AlbumEntity.isPresent()) {
            return albumMapper.mapToDto(AlbumEntity.get());
        }
        return null;
    }

    private AlbumEntity getAlbumEntity(Long id){
        Optional<AlbumEntity> albumEntity = albumRepository.findById(id);
        if (albumEntity.isPresent()) {
            return albumEntity.get();
        }
        return null;
    }

    private ArtistEntity getArtistEntity(Long id){
        Optional<ArtistEntity> artistEntity = artistRepository.findById(id);
        if (artistEntity.isPresent()) {
            return artistEntity.get();
        }
        return null;
    }

    @Transactional
    public AlbumResponseDTO createAlbum(AlbumRequestDTO albumRequestDTO) {
        AlbumEntity albumEntity = albumMapper.mapToEntity(albumRequestDTO);
        GenreEntity genreEntity = serviceHelper.getGenreEntity(albumRequestDTO.getGenreId());
        PublisherEntity publisherEntity = serviceHelper.getPublisherEntity(albumRequestDTO.getPublisherId());

        albumEntity.setGenre(genreEntity);
        albumEntity.setPublisher(publisherEntity);
        albumEntity = albumRepository.save(albumEntity);

        return albumMapper.mapToDto(albumEntity);
    }

    @Transactional
    public AlbumResponseDTO updateAlbum(Long id, AlbumRequestDTO albumInput){
        AlbumEntity existingAlbumEntity = getAlbumEntity(id);
        GenreEntity genreEntity = serviceHelper.getGenreEntity(albumInput.getGenreId());
        PublisherEntity publisherEntity = serviceHelper.getPublisherEntity(albumInput.getPublisherId());

        existingAlbumEntity.setTitle(albumInput.getTitle());
        existingAlbumEntity.setReleaseYear(albumInput.getReleaseYear());
        existingAlbumEntity.setGenre(genreEntity);
        existingAlbumEntity.setPublisher(publisherEntity);

        albumRepository.save(existingAlbumEntity);
        return albumMapper.mapToDto(existingAlbumEntity);
    }

    public void deleteAlbum(Long id) {
        try{
            AlbumEntity existingAlbumEntity = getAlbumEntity(id);
            albumRepository.delete(existingAlbumEntity);
        } catch (IndexOutOfBoundsException ex) {
        }
    }

    @Transactional
    public void linkArtist(Long albumId, Long artistId){
        AlbumEntity existingAlbumEntity = getAlbumEntity(albumId);
        ArtistEntity existingArtistEntity = getArtistEntity(artistId);

        existingAlbumEntity.getArtists().add(existingArtistEntity);
        existingArtistEntity.getAlbums().add(existingAlbumEntity);

        albumRepository.save(existingAlbumEntity);
        artistRepository.save(existingArtistEntity);
    }

    @Transactional
    public void unlinkArtist(Long albumId, Long artistId){
        AlbumEntity existingAlbumEntity = getAlbumEntity(albumId);
        ArtistEntity existingArtistEntity = getArtistEntity(artistId);

        existingAlbumEntity.getArtists().remove(existingArtistEntity);

        albumRepository.save(existingAlbumEntity);
    }


}
