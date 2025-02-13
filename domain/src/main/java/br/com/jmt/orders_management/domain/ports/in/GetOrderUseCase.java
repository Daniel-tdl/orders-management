package br.com.jmt.orders_management.domain.ports.in;


import br.com.jmt.orders_management.domain.model.dto.OrderDto;

public interface GetOrderUseCase {
    OrderDto getOrders(Integer page, Integer size);
}
