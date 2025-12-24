package nl.novi.vinylshop.controllers;

import jakarta.validation.Valid;
import nl.novi.vinylshop.dtos.request.StockRequestDTO;
import nl.novi.vinylshop.dtos.response.StockResponseDTO;
import nl.novi.vinylshop.helpers.UrlHelper;
import nl.novi.vinylshop.services.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums/{albumId}/stock")
public class StockController {

    private final StockService stockService;
    private final UrlHelper urlHelper;

    public StockController(StockService stockService, UrlHelper urlHelper) {
        this.stockService = stockService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<StockResponseDTO>> getAllStocks(@PathVariable Long albumId) {
        List<StockResponseDTO> stockItems = stockService.findAllStocks(albumId);
        return ResponseEntity.ok(stockItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockResponseDTO> getStockById(@PathVariable Long albumId, @PathVariable Long id) {
        StockResponseDTO stockItems = stockService.findStockById(albumId, id);
        if (stockItems == null) {
            return ResponseEntity.notFound().build(); // check of genre bestaat anders hier dus 404 Not Found
        }
        return new ResponseEntity<StockResponseDTO>(stockItems, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StockResponseDTO> createStock(@PathVariable Long albumId, @Valid @RequestBody StockRequestDTO stockInput) {
        StockResponseDTO newStock = stockService.createStock(albumId, stockInput);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newStock.getId())).body(newStock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockResponseDTO> updateStock(@PathVariable Long id, @Valid @RequestBody StockRequestDTO stockInput) {
        StockResponseDTO updatedStock = stockService.updateStock(id, stockInput);
        return new ResponseEntity<>(updatedStock, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
