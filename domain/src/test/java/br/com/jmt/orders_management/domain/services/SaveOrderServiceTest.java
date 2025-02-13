package br.com.jmt.orders_management.domain.services;

import br.com.jmt.orders_management.domain.model.event.OrderEvent;
import br.com.jmt.orders_management.domain.ports.out.GetOrderPort;
import br.com.jmt.orders_management.domain.ports.out.SaveOrderPort;
import br.com.jmt.orders_management.domain.util.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class SaveOrderServiceTest {

    @Mock
    private SaveOrderPort orderPort;

    @Mock
    private GetOrderPort getOrderPort;

    @InjectMocks
    private SaveOrderService service;

    @Test
    void givenExecuteWithSuccess() {
        Mockito.doNothing().when(orderPort).saveOrder(any());
        service.saveOrder(getRequestMock());
        Mockito.verify(orderPort, Mockito.times(1)).saveOrder(any());
    }

    private OrderEvent getRequestMock() {

        return OrderEvent.builder()
                .code("1")
                .date(DateUtils.format(LocalDateTime.now()))
                .products(
                        List.of(
                                OrderEvent.Product.builder()
                                        .code("1")
                                        .description("Teste 1")
                                        .price(10.2)
                                        .build(),
                                OrderEvent.Product.builder()
                                        .code("2")
                                        .description("Teste 3")
                                        .price(10.3)
                                        .build()
                        )
                )
                .build();
    }
}
