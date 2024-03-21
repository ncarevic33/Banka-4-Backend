package rs.edu.raf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.model.dto.CardNameDto;
import rs.edu.raf.model.dto.CardResponseDto;
import rs.edu.raf.model.dto.CreateCardDto;
import rs.edu.raf.service.CardService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/block/{cardNumber}")
    public ResponseEntity<Void> blockCard(@PathVariable("cardNumber") String cardNumber){
        cardService.blockCard(cardNumber);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCard(@RequestBody CreateCardDto createCardDto){
        return new ResponseEntity<>(cardService.createCard(createCardDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CardResponseDto>> getAllCards(@RequestAttribute("userId") Long userId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long permission = 0L;
        if (authentication != null && authentication.getPrincipal() instanceof String) {
            List<? extends GrantedAuthority> authorityList = new ArrayList<>(authentication.getAuthorities());
            permission = Long.parseLong(authorityList.get(0).getAuthority());
        }
        if(permission == 0L)
            return new ResponseEntity<>(cardService.getAllCardsForUser(userId), HttpStatus.OK);

        return new ResponseEntity<>(cardService.getAllCards(), HttpStatus.OK);
    }

    @GetMapping("/names")
    public ResponseEntity<CardNameDto> getCardNames(){
        return new ResponseEntity<>(cardService.getCardNames(), HttpStatus.OK);
    }

}
