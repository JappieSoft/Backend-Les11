package nl.novi.vinylshop.services;

import nl.novi.vinylshop.entities.PublisherEntity;
import nl.novi.vinylshop.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<PublisherEntity> findAllPublishers() {
        return publisherRepository.findAll();
    }

    public PublisherEntity findPublisherById(Long id) {
        Optional<PublisherEntity> PublisherEntity = publisherRepository.findById(id);
        if (PublisherEntity.isPresent()) {
            return PublisherEntity.get();
        }
        return null;
    }

    public PublisherEntity createPublisher(PublisherEntity PublisherEntity) {
        publisherRepository.save(PublisherEntity);
        return PublisherEntity;
    }

    public PublisherEntity updatePublisher(Long id, PublisherEntity publisherInput){
        PublisherEntity existingPublisherEntity = findPublisherById(id);

        existingPublisherEntity.setName(publisherInput.getName());
        existingPublisherEntity.setAddress(publisherInput.getAddress());
        existingPublisherEntity.setContactDetails(publisherInput.getContactDetails());

        publisherRepository.save(existingPublisherEntity);

        return existingPublisherEntity;
    }

    public void deletePublisher(Long id) {
        try{
            PublisherEntity existingPublisherEntity = findPublisherById(id);
            publisherRepository.delete(existingPublisherEntity);
        } catch (IndexOutOfBoundsException ex) {
        }
    }
}