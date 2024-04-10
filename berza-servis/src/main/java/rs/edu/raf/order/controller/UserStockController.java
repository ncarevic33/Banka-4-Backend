package rs.edu.raf.order.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.edu.raf.order.service.OrderService;
import rs.edu.raf.order.service.UserStockService;

@RestController
@RequestMapping("/user-stocks")
@Tag(name = "UserStocks", description = "Operations related to user stocks.")
@AllArgsConstructor
@SecurityRequirement(name = "jwt")
@CrossOrigin(origins = "*")
public class UserStockController {

    private final UserStockService userStockService;
    private final OrderService orderService;    // for calculating current bid and ask prices

}
