package br.com.jmt.orders_management.domain.ports.in;


import br.com.jmt.orders_management.domain.model.request.OrderRequest;

import java.util.List;

public interface CreateOrderUseCase {
    void createOrders(List<OrderRequest> requests);
}
