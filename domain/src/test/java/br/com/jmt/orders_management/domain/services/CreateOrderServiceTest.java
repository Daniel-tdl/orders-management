package br.com.jmt.orders_management.domain.services;

import br.com.jmt.orders_management.domain.mapper.OrderMapper;
import br.com.jmt.orders_management.domain.mapper.OrderMapperImpl;
import br.com.jmt.orders_management.domain.model.request.OrderRequest;
import br.com.jmt.orders_management.domain.ports.out.OrderPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CreateOrderServiceTest {

    @Mock
    private OrderPort orderPort;

    @InjectMocks
    private OrderMapper orderMapper = new OrderMapperImpl();

    @InjectMocks
    private CreateOrderService service;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(service, "orderMapper", orderMapper);
    }

    @Test
    void givenExecuteWithSuccess() {
        Mockito.doNothing().when(orderPort).send(any());
        service.createOrders(getRequestMock());
        Mockito.verify(orderPort, Mockito.times(1)).send(any());
    }

    private List<OrderRequest> getRequestMock() {
        OrderRequest orderRequest = OrderRequest.builder()
                .code("1")
                .date(LocalDateTime.now())
                .products(
                        List.of(
                                OrderRequest.Product.builder()
                                        .code("1")
                                        .description("Teste 1")
                                        .price(10.2)
                                        .build(),
                                OrderRequest.Product.builder()
                                        .code("2")
                                        .description("Teste 3")
                                        .price(10.3)
                                        .build()
                        )
                )
                .build();

        return List.of(orderRequest);
    }
}
