package nl.novi.vinylshop.dtos.mapper;

import nl.novi.vinylshop.dtos.request.PublisherRequestDTO;
import nl.novi.vinylshop.dtos.response.PublisherResponseDTO;
import nl.novi.vinylshop.entities.PublisherEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PublisherMapper implements DtoMapper<PublisherResponseDTO, PublisherRequestDTO, PublisherEntity>{

    @Override
    public PublisherResponseDTO mapToDto(PublisherEntity model) {
        if (model == null) return null;

        PublisherResponseDTO dto = new PublisherResponseDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setAddress(model.getAddress());
        dto.setContactDetails(model.getContactDetails());

        return dto;
    }

    @Override
    public List<PublisherResponseDTO> mapToDto(List<PublisherEntity> models) {
        if (models == null) return null;

        List<PublisherResponseDTO> dtos = new ArrayList<>();
        for (PublisherEntity model : models) {
            dtos.add(mapToDto(model));
        }

        return dtos;
    }

    @Override
    public PublisherEntity mapToEntity(PublisherRequestDTO publisherModel) {
        if (publisherModel == null) return null;

        PublisherEntity entity = new PublisherEntity();
        entity.setName(publisherModel.getName());
        entity.setAddress(publisherModel.getAddress());
        entity.setContactDetails(publisherModel.getContactDetails());

        return entity;
    }
}
