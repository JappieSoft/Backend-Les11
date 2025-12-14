package nl.novi.vinylshop.repository;

import nl.novi.vinylshop.entities.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {
    List<AlbumEntity> findByStockItemsNotEmpty();

    List<AlbumEntity> findByStockItemsEmpty();

    List<AlbumEntity> findByGenre_Id(Long genreId);
}
