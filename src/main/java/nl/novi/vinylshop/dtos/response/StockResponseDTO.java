package nl.novi.vinylshop.dtos.response;

public class StockResponseDTO {
    private Long albumId;
    private String condition;
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
