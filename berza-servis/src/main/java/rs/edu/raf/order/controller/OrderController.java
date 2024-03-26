package rs.edu.raf.order.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.order.dto.OrderRequest;
import rs.edu.raf.order.model.Order;
import rs.edu.raf.order.service.OrderService;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "Orders for stock market")
@AllArgsConstructor
@SecurityRequirement(name = "jwt")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<Order> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Order> GetOrdersForUser(@PathVariable String userId) {
        return new ResponseEntity<>(orderService.getOrdersForUser(userId), HttpStatus.OK);
    }

    @PostMapping("/place-order")
    public ResponseEntity<Order> placeOrder(@RequestBody @Validated OrderRequest order) {
        return new ResponseEntity<>(orderService.placeOrder(order), HttpStatus.OK);
    }


}
