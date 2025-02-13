package br.com.jmt.orders_management.infrastructure.adapter;

import br.com.jmt.orders_management.domain.model.event.OrderEvent;
import br.com.jmt.orders_management.domain.ports.out.OrderPort;
import br.com.jmt.orders_management.domain.util.QueueConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderMessageAdapter implements OrderPort {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(OrderEvent orderEvent) {
        rabbitTemplate.convertAndSend(QueueConstant.CREATE_ORDER, orderEvent);
        log.info("Pedido {} envido para a fila co sucesso. ", orderEvent.getCode());
    }
}
