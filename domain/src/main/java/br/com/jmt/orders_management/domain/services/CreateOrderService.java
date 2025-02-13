package br.com.jmt.orders_management.domain.services;

import br.com.jmt.orders_management.domain.mapper.OrderMapper;
import br.com.jmt.orders_management.domain.model.event.OrderEvent;
import br.com.jmt.orders_management.domain.model.request.OrderRequest;
import br.com.jmt.orders_management.domain.ports.in.OrderUseCase;
import br.com.jmt.orders_management.domain.ports.out.OrderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateOrderService implements OrderUseCase {

    private final OrderPort orderPort;
    private final OrderMapper orderMapper;

    @Override
    public void createOrders(List<OrderRequest> requests) {
        log.info("Iniciando envio de {} pedidos para a fila de integração.", requests.size());
        try {
            requests.forEach(this::sendOrder);
            log.info("Finalizando envio de {} pedidos para a fila de integração.", requests.size());
        } catch (RuntimeException e) {
            var msgError = "Ocorreu um erro ao processar a remessa de pedidos.";
            log.error(Objects.nonNull(e.getMessage()) ? e.getMessage() : msgError);
            throw new RuntimeException(msgError);
        }
    }

    private void sendOrder(OrderRequest orderRequest) {
        OrderEvent event = orderMapper.buildOrderRequestToEvent(orderRequest);
        orderPort.send(event);
    }
}