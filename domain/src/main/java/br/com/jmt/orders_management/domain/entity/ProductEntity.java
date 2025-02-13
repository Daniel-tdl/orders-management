package br.com.jmt.orders_management.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
@With
@Data
@Builder
@Entity
@Table(name = "produto")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo")
    private String code;

    @Column(name = "descricao")
    private String description;

    @Column(name = "preco")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", nullable=false)
    private OrderEntity order;
}
