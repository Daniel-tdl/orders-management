package br.com.jmt.orders_management.application.controller;

import br.com.jmt.orders_management.domain.model.dto.OrderDto;
import br.com.jmt.orders_management.domain.model.request.OrderRequest;
import br.com.jmt.orders_management.domain.ports.in.CreateOrderUseCase;
import br.com.jmt.orders_management.domain.ports.in.GetOrderUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

  private CreateOrderUseCase createOrderUseCase;
  private GetOrderUseCase orderUseCase;

  @PostMapping("/create")
  public ResponseEntity save(@Valid @RequestBody List<OrderRequest> requests) {
    createOrderUseCase.createOrders(requests);
    return ResponseEntity.accepted().build();
  }

  @GetMapping
  public ResponseEntity<OrderDto> getOrder(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                           @RequestParam(value = "size", defaultValue = "10") Integer size) {
    return ResponseEntity.ok(orderUseCase.getOrders(page, size));
  }
}
