package com.uchiha.gearshop.common.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {
    private int id;
    private String name;
    private int available;
    private String description;
    private Float price;
    private Float price_old;
    private int hot;
    private int special;
    private int is_new;
    private String photo;
    private int stock;
    private int category_id;
    private int manufacturer_id;
}
