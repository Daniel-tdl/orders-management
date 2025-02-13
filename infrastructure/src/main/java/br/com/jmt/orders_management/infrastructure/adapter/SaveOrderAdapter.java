package br.com.jmt.orders_management.infrastructure.adapter;

import br.com.jmt.orders_management.domain.entity.OrderEntity;
import br.com.jmt.orders_management.domain.entity.ProductEntity;
import br.com.jmt.orders_management.domain.mapper.OrderMapper;
import br.com.jmt.orders_management.domain.model.dto.OrderDto;
import br.com.jmt.orders_management.domain.model.event.OrderEvent;
import br.com.jmt.orders_management.domain.ports.out.GetOrderPort;
import br.com.jmt.orders_management.domain.ports.out.SaveOrderPort;
import br.com.jmt.orders_management.infrastructure.repository.OrderRepository;
import br.com.jmt.orders_management.infrastructure.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveOrderAdapter implements SaveOrderPort, GetOrderPort {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public void saveOrder(OrderEvent orderEvent) {

        var orderFromDbExist = orderRepository.findByCode(orderEvent.getCode()).orElse(null);
        if(Objects.nonNull(orderFromDbExist)) throw new RuntimeException("Pedido já cadastrado na base de dados.");

        var order = orderMapper.buildOrderEventToEntity(orderEvent);
        var productEntities = orderMapper.buildOrderEventToProductEntity(orderEvent.getProducts());
        var orderFromDb = orderRepository.saveAndFlush(order);
        productEntities.forEach(productEntity -> productEntity.setOrder(orderFromDb));
        productRepository.saveAll(productEntities);
        log.info("Pedido {} salvo na base com sucesso.", orderEvent.getCode());
    }

    @Override
    public OrderDto getOrders(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        var entities = orderRepository.findAll(pageRequest);
//        var ids = entities.getContent().stream()
//                .map(OrderEntity::getProducts)
//                .flatMap(Collection::stream)
//                .mapToDouble(ProductEntity::getId)
//                .collect()

//        var products = productRepository.findByIdIn(List.of(1));
        return buildOrderResponseToDTO(entities);
    }

    private OrderDto buildOrderResponseToDTO(Page<OrderEntity> entities) {
        var page = entities.getPageable().isPaged() ? entities.getPageable().getPageNumber() : 0;
        return OrderDto.builder()
                .pagination(OrderDto.Pagination.builder()
                        .page(page)
                        .totalPages(entities.getTotalPages())
                        .sizeItems(entities.getTotalElements())
                        .build()
                )
                .contents(buildContents(entities.getContent()))
                .build();
    }

    private List<OrderDto.Content> buildContents(List<OrderEntity> entities) {
        List<OrderDto.Content> contents = new ArrayList<>();
        for (OrderEntity entity : entities) {
            var order = orderMapper.buildOrderEntityToDto(entity);
            var productsFilter = orderMapper.buildOrderEntityToProductDto(entity.getProducts());
            order.setProducts(productsFilter);
            contents.add(order);
        }
        return contents;
    }
}
