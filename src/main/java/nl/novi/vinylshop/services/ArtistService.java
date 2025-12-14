package nl.novi.vinylshop.services;

import nl.novi.vinylshop.dtos.mapper.ArtistMapper;
import nl.novi.vinylshop.dtos.request.ArtistRequestDTO;
import nl.novi.vinylshop.dtos.response.ArtistResponseDTO;
import nl.novi.vinylshop.entities.ArtistEntity;
import nl.novi.vinylshop.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

    @Service
    public class ArtistService {

        private final ArtistMapper artistMapper;
        private final ArtistRepository artistRepository;

        public ArtistService(ArtistMapper artistMapper, ArtistRepository artistRepository) {
            this.artistMapper = artistMapper;
            this.artistRepository = artistRepository;
        }

        public List<ArtistResponseDTO> findAllArtists() {
            return artistMapper.mapToDto(artistRepository.findAll());
        }

        public ArtistResponseDTO findArtistById(Long id) {
            Optional<ArtistEntity> ArtistEntity = artistRepository.findById(id);
            if (ArtistEntity.isPresent()) {
                return artistMapper.mapToDto(ArtistEntity.get());
            }
            return null;
        }

        public ArtistResponseDTO createArtist(ArtistRequestDTO artistRequestDTO) {
            ArtistEntity artistEntity = artistMapper.mapToEntity(artistRequestDTO);
            artistEntity = artistRepository.save(artistEntity);

            return artistMapper.mapToDto(artistEntity);
        }

        private ArtistEntity getArtistEntity(Long id){
            Optional<ArtistEntity> artistEntity = artistRepository.findById(id);
            if (artistEntity.isPresent()) {
                return artistEntity.get();
            }
            return null;
        }

        public ArtistResponseDTO updateArtist(Long id, ArtistRequestDTO artistInput){
            ArtistEntity existingArtistEntity = getArtistEntity(id);

            existingArtistEntity.setName(artistInput.getName());
            existingArtistEntity.setBiography(artistInput.getBiography());

            artistRepository.save(existingArtistEntity);

            return artistMapper.mapToDto(existingArtistEntity);
        }

        public void deleteArtist(Long id) {
            try{
                ArtistEntity existingArtistEntity = getArtistEntity(id);
                artistRepository.delete(existingArtistEntity);
            } catch (IndexOutOfBoundsException ex) {
            }
        }

}
