package infrastructure.adapter;

import br.com.jmt.orders_management.domain.entity.OrderEntity;
import br.com.jmt.orders_management.domain.entity.ProductEntity;
import br.com.jmt.orders_management.domain.mapper.OrderMapper;
import br.com.jmt.orders_management.domain.mapper.OrderMapperImpl;
import br.com.jmt.orders_management.domain.model.event.OrderEvent;
import br.com.jmt.orders_management.domain.util.DateUtils;
import br.com.jmt.orders_management.infrastructure.adapter.SaveOrderAdapter;
import br.com.jmt.orders_management.infrastructure.repository.OrderRepository;
import br.com.jmt.orders_management.infrastructure.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class SaveOrderAdapterTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderMapper orderMapper = new OrderMapperImpl();

    @InjectMocks
    private SaveOrderAdapter service;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(service, "orderMapper", orderMapper);
    }

    @Test
    void givenExecuteWithSuccess() {
        Mockito.when(orderRepository.findByCode(any())).thenReturn(Optional.empty());
        service.saveOrder(getRequestMock());
    }

    @Test
    void givenExecuteWithError() {

        Mockito.when(orderRepository.findByCode(any())).thenReturn(Optional.of(getEntityMock()));
        assertThrows(RuntimeException.class, () -> service.saveOrder(getRequestMock()));
    }

    @Test
    void givenExecuteGetOrdersWithSuccess() {
        Page<OrderEntity> ordersPage = new PageImpl<>(List.of(getEntityMock()), PageRequest.of(0, 10), 1);
        Mockito.when(orderRepository.findAll(any(PageRequest.class))).thenReturn(ordersPage);
        var orders = service.getOrders(0, 10);
        Assertions.assertNotNull(orders);
        Assertions.assertEquals(1, orders.getContents().size());
    }

    private OrderEntity getEntityMock() {

        return OrderEntity.builder()
                .code("1")
                .date(LocalDateTime.now())
                .products(
                        List.of(
                                ProductEntity.builder()
                                        .code("1")
                                        .description("Teste 1")
                                        .price(10.2)
                                        .build(),
                                ProductEntity.builder()
                                        .code("2")
                                        .description("Teste 3")
                                        .price(10.3)
                                        .build()
                        )
                )
                .build();
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
