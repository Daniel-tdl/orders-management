package br.com.jmt.orders_management.domain.services;

import br.com.jmt.orders_management.domain.model.event.OrderEvent;
import br.com.jmt.orders_management.domain.ports.in.SaveOrderUseCase;
import br.com.jmt.orders_management.domain.ports.out.SaveOrderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaveOrderService implements SaveOrderUseCase {

    private final SaveOrderPort orderPort;

    @Override
    public void saveOrder(OrderEvent orderEvent) {
        orderPort.saveOrder(orderEvent);
    }
}