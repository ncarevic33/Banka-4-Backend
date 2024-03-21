package rs.edu.raf.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.model.dto.CreditRequestDto;
import rs.edu.raf.model.dto.DetailedCreditDto;
import rs.edu.raf.service.CreditService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/credit")
public class CreditController {

    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping("/apply")
    public ResponseEntity<String> applyForCredit(@RequestBody CreditRequestDto creditRequestDto){
        return new ResponseEntity(creditService.applyForCredit(creditRequestDto), HttpStatus.OK);
    }

    @GetMapping("/deny/{id}")
    public ResponseEntity<String> denyCreditRequest(@PathVariable("id") Long id){
        return new ResponseEntity<>(creditService.dennyCreditRequest(id), HttpStatus.OK);
    }

    @GetMapping("/approve/{id}")
    public ResponseEntity<String> approveCredit(@PathVariable("id") Long id){
        System.out.println("usao sam u kontroller ");
        return new ResponseEntity<>(creditService.approveCreditRequest(id), HttpStatus.OK);
    }

    @GetMapping("/all/{status}")
    public ResponseEntity<List<CreditRequestDto>> getAllCreditRequestsForUser(@RequestAttribute("userId") Long userId, @PathVariable("status") String status){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long permission = 0L;
        if (authentication != null && authentication.getPrincipal() instanceof String) {
            List<? extends GrantedAuthority> authorityList = new ArrayList<>(authentication.getAuthorities());
            permission = Long.parseLong(authorityList.get(0).getAuthority());
            System.out.println("Permission iz CreditControllera" + permission);
        }

        if(permission == 0L)
            return new ResponseEntity<>(creditService.getAllCreditRequestForUser(userId, status), HttpStatus.OK);

        return new ResponseEntity<>(creditService.getAllCreditRequests(status), HttpStatus.OK);
    }

    @GetMapping("/detailed-credit/creditRequestId/{creditRequestId}")
    public ResponseEntity<DetailedCreditDto> getDetailedCredit(@PathVariable("creditRequestId") Long creditRequestId){
        return new ResponseEntity<>(creditService.getDetailedCredit(creditRequestId), HttpStatus.OK);
    }

}
