package com.example.EtsyProject.EtsyProject.service.requests;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchProductRequest {
    private Double min_price;
    private Double max_price;
    private String sortType;
    private Integer outOfStock;
}
