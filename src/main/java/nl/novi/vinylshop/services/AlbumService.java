package nl.novi.vinylshop.services;

import nl.novi.vinylshop.dtos.mapper.AlbumMapper;
import nl.novi.vinylshop.dtos.request.AlbumRequestDTO;
import nl.novi.vinylshop.dtos.response.AlbumResponseDTO;
import nl.novi.vinylshop.entities.AlbumEntity;
import nl.novi.vinylshop.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private final AlbumMapper albumMapper;
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumMapper albumMapper, AlbumRepository albumRepository) {
        this.albumMapper = albumMapper;
        this.albumRepository = albumRepository;
    }

    public List<AlbumResponseDTO> findAllAlbums() {
        return albumMapper.mapToDto(albumRepository.findAll());
    }

    public AlbumResponseDTO findAlbumById(Long id) {
        Optional<AlbumEntity> AlbumEntity = albumRepository.findById(id);
        if (AlbumEntity.isPresent()) {
            return albumMapper.mapToDto(AlbumEntity.get());
        }
        return null;
    }

    public AlbumResponseDTO createAlbum(AlbumRequestDTO albumRequestDTO) {
        AlbumEntity albumEntity = albumMapper.mapToEntity(albumRequestDTO);
        albumEntity = albumRepository.save(albumEntity);

        return albumMapper.mapToDto(albumEntity);
    }

    private AlbumEntity getAlbumEntity(Long id){
        Optional<AlbumEntity> albumEntity = albumRepository.findById(id);
        if (albumEntity.isPresent()) {
            return albumEntity.get();
        }
        return null;
    }

    public AlbumResponseDTO updateAlbum(Long id, AlbumRequestDTO albumInput){
        AlbumEntity existingAlbumEntity = getAlbumEntity(id);

        existingAlbumEntity.setTitle(albumInput.getTitle());
        existingAlbumEntity.setReleaseYear(albumInput.getReleaseYear());

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
}
