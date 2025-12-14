package nl.novi.vinylshop.dtos.mapper;

import nl.novi.vinylshop.dtos.request.GenreRequestDTO;
import nl.novi.vinylshop.dtos.response.GenreResponseDTO;
import nl.novi.vinylshop.entities.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper implements DtoMapper<GenreResponseDTO, GenreRequestDTO, GenreEntity>{

    @Override
    public GenreResponseDTO mapToDto(GenreEntity model) {
        if (model == null) return null;

        GenreResponseDTO dto = new GenreResponseDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());

        return dto;
    }

    @Override
    public List<GenreResponseDTO> mapToDto(List<GenreEntity> models) {
        if (models == null) return null;

        List<GenreResponseDTO> dtos = new ArrayList<>();
        for (GenreEntity model : models) {
            dtos.add(mapToDto(model));
        }
        return dtos;
    }

    @Override
    public GenreEntity mapToEntity(GenreRequestDTO genreModel) {
        if (genreModel == null) return null;
        GenreEntity entity = new GenreEntity();
        entity.setName(genreModel.getName());
        entity.setDescription(genreModel.getDescription());

        return entity;
    }
}
