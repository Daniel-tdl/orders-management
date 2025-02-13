package br.com.jmt.orders_management.infrastructure.adapter;

import br.com.jmt.orders_management.domain.model.event.OrderEvent;
import br.com.jmt.orders_management.domain.ports.out.SaveOrderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveOrderAdapter implements SaveOrderPort {

    @Override
    public void saveOrder(OrderEvent orderEvent) {

    }
}
