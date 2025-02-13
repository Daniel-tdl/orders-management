package br.com.jmt.orders_management.domain.ports.out;


import br.com.jmt.orders_management.domain.model.dto.OrderDto;
import br.com.jmt.orders_management.domain.model.event.OrderEvent;

public interface GetOrderPort {
    OrderDto getOrders(Integer page, Integer size);
}

