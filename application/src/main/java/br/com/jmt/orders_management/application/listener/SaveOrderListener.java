package br.com.jmt.orders_management.application.listener;

import br.com.jmt.orders_management.domain.model.event.OrderEvent;
import br.com.jmt.orders_management.domain.ports.in.SaveOrderUseCase;
import br.com.jmt.orders_management.domain.util.QueueConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SaveOrderListener {

    private final SaveOrderUseCase orderUseCase;

    @RabbitListener(queues = QueueConstant.CREATE_ORDER)
    public void saveOrderCreated(OrderEvent event) {
        log.info("Recebendo o pedido {}", event.getCode());
        orderUseCase.saveOrder(event);
    }
}
