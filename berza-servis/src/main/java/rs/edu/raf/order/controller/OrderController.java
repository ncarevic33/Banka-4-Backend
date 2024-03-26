package rs.edu.raf.order.controller;

import io.swagger.annotations.ApiOperation;
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

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "Operations related to orders.")
@AllArgsConstructor
@SecurityRequirement(name = "jwt")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    @ApiOperation(value = "Returns all orders.")
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns orders for user.")
    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> GetOrdersForUser(@PathVariable Long userId) {
        return new ResponseEntity<>(orderService.getOrdersForUser(userId), HttpStatus.OK);
    }

    @ApiOperation(value = "Places order.")
    @PostMapping("/place-order")
    public ResponseEntity<Order> placeOrder(@RequestBody @Validated OrderRequest order) {
        return new ResponseEntity<>(orderService.placeOrder(order), HttpStatus.OK);
    }


}
