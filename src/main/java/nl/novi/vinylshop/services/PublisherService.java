package nl.novi.vinylshop.services;

import jakarta.transaction.Transactional;
import nl.novi.vinylshop.dtos.mapper.PublisherMapper;
import nl.novi.vinylshop.dtos.request.PublisherRequestDTO;
import nl.novi.vinylshop.dtos.response.PublisherResponseDTO;
import nl.novi.vinylshop.entities.AlbumEntity;
import nl.novi.vinylshop.entities.PublisherEntity;
import nl.novi.vinylshop.repository.AlbumRepository;
import nl.novi.vinylshop.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherMapper publisherMapper;
    private final PublisherRepository publisherRepository;
    private final AlbumRepository albumRepository;

    public PublisherService(PublisherMapper publisherMapper, PublisherRepository publisherRepository, AlbumRepository albumRepository) {
        this.publisherMapper = publisherMapper;
        this.publisherRepository = publisherRepository;
        this.albumRepository = albumRepository;
    }

    public List<PublisherResponseDTO> findAllPublishers() {
        return publisherMapper.mapToDto(publisherRepository.findAll());
    }

    public PublisherResponseDTO findPublisherById(Long id) {
        Optional<PublisherEntity> PublisherEntity = publisherRepository.findById(id);
        if (PublisherEntity.isPresent()) {
            return publisherMapper.mapToDto(PublisherEntity.get());
        }
        return null;
    }

    public PublisherResponseDTO createPublisher(PublisherRequestDTO publisherRequestDTO) {
        PublisherEntity publisherEntity = publisherMapper.mapToEntity(publisherRequestDTO);
        publisherEntity = publisherRepository.save(publisherEntity);

        return publisherMapper.mapToDto(publisherEntity);
    }

    private PublisherEntity getPublisherEntity(Long id){
        Optional<PublisherEntity> publisherEntity = publisherRepository.findById(id);
        if (publisherEntity.isPresent()) {
            return publisherEntity.get();
        }
        return null;
    }

    public PublisherResponseDTO updatePublisher(Long id, PublisherRequestDTO publisherInput){
        PublisherEntity existingPublisherEntity = getPublisherEntity(id);

        existingPublisherEntity.setName(publisherInput.getName());
        existingPublisherEntity.setAddress(publisherInput.getAddress());
        existingPublisherEntity.setContactDetails(publisherInput.getContactDetails());

        publisherRepository.save(existingPublisherEntity);

        return publisherMapper.mapToDto(existingPublisherEntity);
    }

    @Transactional
    public void deletePublisher(Long id) {
        try{
            PublisherEntity existingPublisherEntity = getPublisherEntity(id);

            for(AlbumEntity album : existingPublisherEntity.getAlbums()){
                album.setPublisher(null);
                albumRepository.save(album);
            }

            publisherRepository.delete(existingPublisherEntity);
        } catch (IndexOutOfBoundsException ex) {
        }
    }
}