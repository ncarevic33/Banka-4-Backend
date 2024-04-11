package rs.edu.raf.futures.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.futures.service.FuturesService;
import rs.edu.raf.futures.dto.FuturesContractDto;

import java.util.List;

@RestController
@RequestMapping("/futures")
@AllArgsConstructor
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "jwt")
public class FuturesController {
    private FuturesService futuresService;

    @GetMapping("/type/{type}")
    @Operation(description = "Get all future contracts by type")
    public ResponseEntity<List<FuturesContractDto>> allFuturesByType(@PathVariable("type") String type) {
        return new ResponseEntity<>(futuresService.findByType(type), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @Operation(description = "Get all future contracts by name")
    public ResponseEntity<List<FuturesContractDto>> allFuturesByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(futuresService.findByName(name),HttpStatus.OK);
    }

    @GetMapping("/kupac")
    @Operation(description = "Get all future contracts for user")
    public ResponseEntity<List<FuturesContractDto>> allFuturesForUser(@RequestAttribute("userId") Long userId) {
        return new ResponseEntity<>(futuresService.findByKupac(userId), HttpStatus.OK);
    }

    @PostMapping("/buy/{id}")
    @Operation(description = "Buy future contract")
    public ResponseEntity<FuturesContractDto> buyFuture(@PathVariable("id") Long id, @RequestAttribute("userId") Long userId) {
        return new ResponseEntity<>(futuresService.buy(id,userId),HttpStatus.OK);
    }
}
