package br.com.jmt.orders_management.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {

    private Pagination pagination;
    private List<Content> contents;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @With
    @Builder
    public static class Pagination {
        private Integer page;
        private Integer totalPages;
        private Long sizeItems;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @With
    public static class Content {
        private Long id;
        private String date;
        private Double totalPrice;
        private List<Product> products;

        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        @Builder
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Product {

            private String code;
            private String description;
            private Double price;
        }
    }
}
