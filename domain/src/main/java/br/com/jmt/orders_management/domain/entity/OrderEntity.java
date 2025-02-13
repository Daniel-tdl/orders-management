package br.com.jmt.orders_management.domain.entity;

import br.com.jmt.orders_management.domain.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
@With
@Data
@Entity
@Table(name = "Pedido")
public class OrderEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", unique = true)
    private String code;

    @Column(name = "data_pedido")
    private LocalDateTime date;

    @Column(name = "total_preco")
    private Double totalPrice;

    @OneToMany(mappedBy="order")
    private Set<ProductEntity> products;
}
