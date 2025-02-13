package br.com.jmt.orders_management.domain.ports.out;


import br.com.jmt.orders_management.domain.model.event.OrderEvent;

public interface OrderPort {
    void send(OrderEvent orderEvent);
}

