package com.uchiha.gearshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Table(name = "bill_detail")
@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BillDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("bill_detail_id")
    private int bill_detail_id;
    @Column(name = "amount")
    private int amount;
    @Column(name = "price")
    private float price;
    @ManyToOne
    @JoinColumn(name = "bill_id")
    private BillEntity bill;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

}
