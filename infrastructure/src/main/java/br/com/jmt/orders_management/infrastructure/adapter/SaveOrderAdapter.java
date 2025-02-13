package br.com.jmt.orders_management.infrastructure.adapter;

import br.com.jmt.orders_management.domain.entity.ProductEntity;
import br.com.jmt.orders_management.domain.mapper.OrderMapper;
import br.com.jmt.orders_management.domain.model.event.OrderEvent;
import br.com.jmt.orders_management.domain.ports.out.SaveOrderPort;
import br.com.jmt.orders_management.infrastructure.repository.OrderRepository;
import br.com.jmt.orders_management.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveOrderAdapter implements SaveOrderPort {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    public void saveOrder(OrderEvent orderEvent) {

        var orderFromDbExist = orderRepository.findByCode(orderEvent.getCode()).orElse(null);
        if(Objects.nonNull(orderFromDbExist)) throw new RuntimeException("Pedido já cadastrado na base de dados.");

        var order = orderMapper.buildOrderEventToEntity(orderEvent);
        var productEntities = orderMapper.buildOrderEventToProductEntity(orderEvent.getProducts());
        var orderFromDb = orderRepository.saveAndFlush(order);
        productEntities.forEach(productEntity -> productEntity.setOrder(orderFromDb));
        productRepository.saveAll(productEntities);
    }
}
