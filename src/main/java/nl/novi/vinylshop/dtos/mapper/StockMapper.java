package nl.novi.vinylshop.dtos.mapper;

import nl.novi.vinylshop.dtos.request.StockRequestDTO;
import nl.novi.vinylshop.dtos.response.StockResponseDTO;
import nl.novi.vinylshop.entities.StockEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StockMapper implements DtoMapper<StockResponseDTO, StockRequestDTO, StockEntity>{

    @Override
    public StockResponseDTO mapToDto(StockEntity model) {
        if (model == null) return null;

        StockResponseDTO dto = new StockResponseDTO();
        dto.setId(model.getId());
        dto.setCondition(model.getCondition());
        dto.setPrice(model.getPrice());

        return dto;
    }

    @Override
    public List<StockResponseDTO> mapToDto(List<StockEntity> models) {
        if (models == null) return null;

        List<StockResponseDTO> dtos = new ArrayList<>();
        for (StockEntity model : models) {
            dtos.add(mapToDto(model));
        }

        return dtos;
    }

    @Override
    public StockEntity mapToEntity(StockRequestDTO stockModel) {
        if (stockModel == null) return null;
        StockEntity entity = new StockEntity();
        entity.setCondition(stockModel.getCondition());
        entity.setPrice(stockModel.getPrice());

        return entity;
    }
}