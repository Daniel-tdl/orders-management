package br.com.jmt.orders_management.domain.ports.in;


import br.com.jmt.orders_management.domain.model.dto.OrderDto;
import br.com.jmt.orders_management.domain.model.event.OrderEvent;

public interface SaveOrderUseCase {
    void saveOrder(OrderEvent event);
}
