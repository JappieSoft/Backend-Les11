package nl.novi.vinylshop.dtos.request;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public class  StockRequestDTO {
    @Id
    private Long albumId;
    private String condition;
    @NotNull
    @Min(0)
    private Double price;

    public Long getId() { return albumId; }
    public void setId(Long albumId) {
        this.albumId = albumId;
    }

    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
