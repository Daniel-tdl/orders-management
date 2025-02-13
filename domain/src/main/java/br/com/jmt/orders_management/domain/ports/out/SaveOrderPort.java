package br.com.jmt.orders_management.domain.ports.out;


import br.com.jmt.orders_management.domain.model.event.OrderEvent;

public interface SaveOrderPort {
    void saveOrder(OrderEvent orderEvent);
}

