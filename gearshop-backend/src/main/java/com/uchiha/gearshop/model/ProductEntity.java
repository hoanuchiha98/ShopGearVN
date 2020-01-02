package com.uchiha.gearshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "product")
@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "available", nullable = true)
    private int available;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "price")
    private Float price;
    @Column(name = "price_old")
    private Float price_old;
    @Column(name = "hot", nullable = true)
    private int hot;
    @Column(name = "special", nullable = true)
    private int special;
    @Column(name = "is_new", nullable = true)
    private int is_new;
    @Column(name = "photo", nullable = true)
    private String photo;
    @Column(name = "stock")
    private int stock;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private ManufacturerEntity manufacturer;

    //     Reference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<BillDetailEntity> billDetails = new HashSet<BillDetailEntity>(0);


}
