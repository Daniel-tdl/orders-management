package br.com.jmt.orders_management.domain.services;

import br.com.jmt.orders_management.domain.model.dto.OrderDto;
import br.com.jmt.orders_management.domain.model.event.OrderEvent;
import br.com.jmt.orders_management.domain.ports.in.GetOrderUseCase;
import br.com.jmt.orders_management.domain.ports.in.SaveOrderUseCase;
import br.com.jmt.orders_management.domain.ports.out.GetOrderPort;
import br.com.jmt.orders_management.domain.ports.out.SaveOrderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaveOrderService implements SaveOrderUseCase, GetOrderUseCase {

    private final SaveOrderPort orderPort;
    private final GetOrderPort getOrderPort;

    @Override
    public void saveOrder(OrderEvent orderEvent) {
        var totalPrice = orderEvent.getProducts()
                .stream()
                .mapToDouble(OrderEvent.Product::getPrice)
                .sum();

        orderEvent.setTotalPrice(totalPrice);
        orderPort.saveOrder(orderEvent);
    }

    @Override
    public OrderDto getOrders(Integer page, Integer size) {
        return getOrderPort.getOrders(page, size);
    }
}