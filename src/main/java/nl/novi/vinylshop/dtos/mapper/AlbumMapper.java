package nl.novi.vinylshop.dtos.mapper;

import nl.novi.vinylshop.dtos.request.AlbumRequestDTO;
import nl.novi.vinylshop.dtos.response.AlbumResponseDTO;
import nl.novi.vinylshop.entities.AlbumEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlbumMapper implements DtoMapper<AlbumResponseDTO, AlbumRequestDTO, AlbumEntity>{

    private final GenreMapper genreMapper;
    private final PublisherMapper publisherMapper;
    private final ArtistMapper artistMapper;

    public AlbumMapper(GenreMapper genreMapper, PublisherMapper publisherMapper, ArtistMapper artistMapper) {
        this.genreMapper = genreMapper;
        this.publisherMapper = publisherMapper;
        this.artistMapper = artistMapper;
    }

    public <D extends AlbumResponseDTO> D mapToDto(AlbumEntity model, D dto) {
        if (model == null) return null;

        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setReleaseYear(model.getReleaseYear());
        dto.setGenre(genreMapper.mapToDto(model.getGenre()));
        dto.setPublisher(publisherMapper.mapToDto(model.getPublisher()));
        dto.setArtists(artistMapper.mapToDto(new ArrayList<>(model.getArtists())));

        return dto;
    }

    @Override
    public AlbumResponseDTO mapToDto(AlbumEntity model) {
        return mapToDto(model, new AlbumResponseDTO());
    }

    @Override
    public List<AlbumResponseDTO> mapToDto(List<AlbumEntity> models) {
        if (models == null) return null;

        List<AlbumResponseDTO> dtos = new ArrayList<>();
        for (AlbumEntity model : models) {
            dtos.add(mapToDto(model));
        }

        return dtos;
    }

    @Override
    public AlbumEntity mapToEntity(AlbumRequestDTO albumModel) {
        if (albumModel == null) return null;

        AlbumEntity entity = new AlbumEntity();
        entity.setTitle(albumModel.getTitle());
        entity.setReleaseYear(albumModel.getReleaseYear());

        return entity;
    }
}