package nl.novi.vinylshop.dtos.mapper;

import nl.novi.vinylshop.dtos.request.ArtistRequestDTO;
import nl.novi.vinylshop.dtos.response.ArtistResponseDTO;
import nl.novi.vinylshop.entities.ArtistEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArtistMapper implements DtoMapper<ArtistResponseDTO, ArtistRequestDTO, ArtistEntity>{

    @Override
    public ArtistResponseDTO mapToDto(ArtistEntity model) {
        if (model == null) return null;

        ArtistResponseDTO dto = new ArtistResponseDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setBiography(model.getBiography());

        return dto;
    }

    @Override
    public List<ArtistResponseDTO> mapToDto(List<ArtistEntity> models) {
        if (models == null) return null;

        List<ArtistResponseDTO> dtos = new ArrayList<>();
        for (ArtistEntity model : models) {
            dtos.add(mapToDto(model));
        }

        return dtos;
    }

    @Override
    public ArtistEntity mapToEntity(ArtistRequestDTO artistModel) {
        if (artistModel == null) return null;

        ArtistEntity entity = new ArtistEntity();
        entity.setName(artistModel.getName());
        entity.setBiography(artistModel.getBiography());

        return entity;
    }
}