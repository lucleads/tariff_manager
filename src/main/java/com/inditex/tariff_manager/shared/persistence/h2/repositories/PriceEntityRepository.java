package com.inditex.tariff_manager.shared.persistence.h2.repositories;

import com.inditex.tariff_manager.shared.persistence.h2.entities.PriceEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceEntityRepository extends JpaRepository<PriceEntity, Integer> {

    @Query("SELECT p FROM PriceEntity p WHERE p.productId = :productId AND p.brandId = :brandId AND :date BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC LIMIT 1")
    Optional<PriceEntity> searchHighestPriorityPriceEntity(
        @Param("productId") int productId,
        @Param("brandId") int brandId,
        @Param("date") LocalDateTime date
    );
}
