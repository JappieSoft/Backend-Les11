package nl.novi.vinylshop.services;

import jakarta.transaction.Transactional;
import nl.novi.vinylshop.dtos.mapper.StockMapper;
import nl.novi.vinylshop.dtos.request.StockRequestDTO;
import nl.novi.vinylshop.dtos.response.StockResponseDTO;
import nl.novi.vinylshop.entities.AlbumEntity;
import nl.novi.vinylshop.entities.StockEntity;
import nl.novi.vinylshop.repository.AlbumRepository;
import nl.novi.vinylshop.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final StockMapper stockMapper;
    private final StockRepository stockRepository;
    private final AlbumRepository albumRepository;

    public StockService(StockMapper stockMapper, StockRepository stockRepository, AlbumRepository albumRepository) {
        this.stockMapper = stockMapper;
        this.stockRepository = stockRepository;
        this.albumRepository = albumRepository;
    }

    public List<StockResponseDTO> findAllStocks(Long albumId) {
        return stockMapper.mapToDto(stockRepository.findByAlbumId(albumId));
    }

    public StockResponseDTO findStockById(Long albumId, Long id) {
        Optional<StockEntity> StockEntity = stockRepository.findByIdAndAlbumId(id, albumId);
        if (StockEntity.isPresent()) {
            return stockMapper.mapToDto(StockEntity.get());
        }
        return null;
    }

    @Transactional
    public StockResponseDTO createStock(Long albumId, StockRequestDTO stockRequestDTO) {
        Optional<AlbumEntity> albumEntity = albumRepository.findById(albumId);
        if (albumEntity.isPresent()) {

        StockEntity stockEntity = stockMapper.mapToEntity(stockRequestDTO);
        stockEntity.setAlbum(albumEntity.get());

        stockEntity = stockRepository.save(stockEntity);
        return stockMapper.mapToDto(stockEntity);
        }
        return null;
    }

    private StockEntity getStockEntity(Long id){
        Optional<StockEntity> stockEntity = stockRepository.findById(id);
        if (stockEntity.isPresent()) {
            return stockEntity.get();
        }
        return null;
    }

    public StockResponseDTO updateStock(Long id, StockRequestDTO stockInput){
        StockEntity existingStockEntity = getStockEntity(id);

        existingStockEntity.setCondition(stockInput.getCondition());
        existingStockEntity.setPrice(stockInput.getPrice());

        stockRepository.save(existingStockEntity);

        return stockMapper.mapToDto(existingStockEntity);
    }

    public void deleteStock(Long id) {
        try{
            StockEntity existingStockEntity = getStockEntity(id);
            stockRepository.delete(existingStockEntity);
        } catch (IndexOutOfBoundsException ex) {
        }
    }
}