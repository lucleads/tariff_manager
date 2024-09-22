package com.inditex.tariff_manager.shared.persistence.h2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRICES")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PriceEntity {

    @Id
    @Column(name = "PRICE_LIST")
    private int priceList;

    @Column(name = "BRAND_ID")
    private int brandId;

    @Setter
    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Setter
    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "PRODUCT_ID")
    private int productId;

    @Column(name = "PRIORITY")
    private int priority;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "CURR")
    private String currency;
}
