package br.com.jmt.orders_management.domain.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {

    private static final String DATETIME_JSON_PATTERN = "yyyy-MM-dd'T'hh:mm:ss.SSSZ";

    @NotEmpty(message = "Informe o codigo do pedido.")
    private String code;

//    @JsonFormat(pattern = DATETIME_JSON_PATTERN)
    @DateTimeFormat(iso = ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Informe a data do pedido.")
    private LocalDateTime date;

    private List<Product> products;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Product {

        @NotEmpty(message = "Informe o codigo do produto.")
        private String code;

        @NotEmpty(message = "Informe a descrição do produto.")
        private String description;

        @NotNull(message = "Informe o valor do produto.")
        private Double price;
    }
}
