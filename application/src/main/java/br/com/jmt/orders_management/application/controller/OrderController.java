package br.com.jmt.orders_management.application.controller;

import br.com.jmt.orders_management.domain.model.request.OrderRequest;
import br.com.jmt.orders_management.domain.ports.in.OrderUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

  private OrderUseCase orderUseCase;

  @PostMapping("/create")
  public ResponseEntity save(@Valid @RequestBody List<OrderRequest> requests) {
    orderUseCase.createOrders(requests);
    return ResponseEntity.accepted().build();
  }
}
