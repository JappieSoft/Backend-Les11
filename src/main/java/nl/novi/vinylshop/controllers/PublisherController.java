package nl.novi.vinylshop.controllers;

import jakarta.validation.Valid;
import nl.novi.vinylshop.dtos.request.PublisherRequestDTO;
import nl.novi.vinylshop.dtos.response.PublisherResponseDTO;
import nl.novi.vinylshop.helpers.UrlHelper;
import nl.novi.vinylshop.services.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;
    private final UrlHelper urlHelper;

    public PublisherController(PublisherService publisherService, UrlHelper urlHelper) {
        this.publisherService = publisherService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<PublisherResponseDTO>> getAllPublishers() {
        List<PublisherResponseDTO> publishers = publisherService.findAllPublishers();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponseDTO> getPublisherById(@PathVariable Long id) {
        PublisherResponseDTO publisher = publisherService.findPublisherById(id);
        return new ResponseEntity<PublisherResponseDTO>(publisher, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PublisherResponseDTO> createPublisher(@Valid @RequestBody PublisherRequestDTO publisherInput) {
        PublisherResponseDTO newPublisher = publisherService.createPublisher(publisherInput);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newPublisher.getId())).body(newPublisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponseDTO> updatePublisher(@PathVariable Long id, @RequestBody @Valid PublisherRequestDTO publisherInput) {
        PublisherResponseDTO updatedPublisher = publisherService.updatePublisher(id, publisherInput);
        return new ResponseEntity<>(updatedPublisher, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
