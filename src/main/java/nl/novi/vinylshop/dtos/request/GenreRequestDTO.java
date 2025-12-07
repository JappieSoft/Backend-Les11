package nl.novi.vinylshop.dtos.request;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class GenreRequestDTO {
    @Id
    private Long id;

    @NotBlank
    @Max(value = 100, message = "Naam mag niet langer zijn dat 100 letters.")
    @Min(value = 3, message = "Naam moet minimaal 3 letters lang zijn.")
    private String name;

    @Max(value = 250, message = "Beschrijving mag niet langer zijn dan 250 letters.")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
