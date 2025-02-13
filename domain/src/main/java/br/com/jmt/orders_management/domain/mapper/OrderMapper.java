package br.com.jmt.orders_management.domain.mapper;

import br.com.jmt.orders_management.domain.entity.OrderEntity;
import br.com.jmt.orders_management.domain.entity.ProductEntity;
import br.com.jmt.orders_management.domain.model.event.OrderEvent;
import br.com.jmt.orders_management.domain.model.request.OrderRequest;
import br.com.jmt.orders_management.domain.util.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "date", expression = "java(parseDateToString(orderRequest))")
    OrderEvent buildOrderRequestToEvent(OrderRequest orderRequest);

    default String parseDateToString(OrderRequest orderRequest) {
        return DateUtils.format(orderRequest.getDate());
    }

    @Mapping(target = "date", expression = "java(parseStringToLocalDateTime(orderEvent))")
    OrderEntity buildOrderEventToEntity(OrderEvent orderEvent);

    List<ProductEntity> buildOrderEventToProductEntity(List<OrderEvent.Product> products);

    default LocalDateTime parseStringToLocalDateTime(OrderEvent orderEvent) {
        return DateUtils.convert(orderEvent.getDate());
    }
}
