package nl.novi.vinylshop.dtos.mapper;

import nl.novi.vinylshop.dtos.response.AlbumExtendedResponseDTO;
import nl.novi.vinylshop.dtos.response.AlbumResponseDTO;
import nl.novi.vinylshop.entities.AlbumEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class AlbumExtendedResponseMapper extends AlbumMapper {
    private final StockMapper stockMapper;

    public AlbumExtendedResponseMapper(GenreMapper genreMapper, PublisherMapper publisherMapper, ArtistMapper artistMapper, StockMapper stockMapper) {
        super(genreMapper, publisherMapper, artistMapper);
        this.stockMapper = stockMapper;
    }

    @Override
    public AlbumResponseDTO mapToDto(AlbumEntity model) {
        AlbumExtendedResponseDTO dto = (AlbumExtendedResponseDTO) super.mapToDto(model);
        if (dto == null) return null;

        dto.setStock(stockMapper.mapToDto(new ArrayList<>(model.getStockItems())));

        return dto;
    }
}