package nl.novi.vinylshop.services;

import nl.novi.vinylshop.dtos.mapper.StockMapper;
import nl.novi.vinylshop.dtos.request.StockRequestDTO;
import nl.novi.vinylshop.dtos.response.StockResponseDTO;
import nl.novi.vinylshop.entities.StockEntity;
import nl.novi.vinylshop.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final StockMapper stockMapper;
    private final StockRepository stockRepository;

    public StockService(StockMapper stockMapper, StockRepository stockRepository) {
        this.stockMapper = stockMapper;
        this.stockRepository = stockRepository;
    }

    public List<StockResponseDTO> findAllStocks() {
        return stockMapper.mapToDto(stockRepository.findAll());
    }

    public StockResponseDTO findStockById(Long id) {
        Optional<StockEntity> StockEntity = stockRepository.findById(id);
        if (StockEntity.isPresent()) {
            return stockMapper.mapToDto(StockEntity.get());
        }
        return null;
    }

    public StockResponseDTO createStock(StockRequestDTO stockRequestDTO) {
        StockEntity stockEntity = stockMapper.mapToEntity(stockRequestDTO);
        stockEntity = stockRepository.save(stockEntity);

        return stockMapper.mapToDto(stockEntity);
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