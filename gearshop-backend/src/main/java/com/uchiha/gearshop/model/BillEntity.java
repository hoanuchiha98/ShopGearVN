package com.uchiha.gearshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table(name = "bill")
@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;
    @Column(name = "code")
    private int code;
    @Column(name = "create_date")
    private Date create_date;
    // Reference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<BillDetailEntity> billDetails = new HashSet<BillDetailEntity>(0);

}
