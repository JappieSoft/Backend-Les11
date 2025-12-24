package nl.novi.vinylshop.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public class  StockRequestDTO {
    private String condition;
    @NotNull
    @Min(0)
    private Double price;

    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
