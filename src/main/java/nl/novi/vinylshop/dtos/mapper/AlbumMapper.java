package nl.novi.vinylshop.dtos.mapper;

import nl.novi.vinylshop.dtos.request.AlbumRequestDTO;
import nl.novi.vinylshop.dtos.response.AlbumResponseDTO;
import nl.novi.vinylshop.entities.AlbumEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlbumMapper implements DtoMapper<AlbumResponseDTO, AlbumRequestDTO, AlbumEntity>{

    @Override
    public AlbumResponseDTO mapToDto(AlbumEntity model) {
        AlbumResponseDTO dto = new AlbumResponseDTO();
        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setReleaseYear(model.getReleaseYear());

        return dto;
    }

    @Override
    public List<AlbumResponseDTO> mapToDto(List<AlbumEntity> models) {
        List<AlbumResponseDTO> dtos = new ArrayList<>();
        for (AlbumEntity model : models) {
            dtos.add(mapToDto(model));
        }

        return dtos;
    }

    @Override
    public AlbumEntity mapToEntity(AlbumRequestDTO albumModel) {
        AlbumEntity entity = new AlbumEntity();
        entity.setTitle(albumModel.getTitle());
        entity.setReleaseYear(albumModel.getReleaseYear());

        return entity;
    }
}