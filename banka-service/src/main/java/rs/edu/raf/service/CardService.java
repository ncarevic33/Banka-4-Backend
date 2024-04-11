package rs.edu.raf.service;

import rs.edu.raf.model.dto.CardNameDto;
import rs.edu.raf.model.dto.CardResponseDto;
import rs.edu.raf.model.dto.CreateCardDto;

import java.util.List;

public interface CardService {
    public String createCard(CreateCardDto createCardDto);
    public void deleteCard(String cardNumber);
    public void blockCard(String cardNumber);
    public void activateCard(String cardNumber);
    public void deactivateCard(String cardNumber);
    public CardNameDto getCardNames();
    public List<CardResponseDto> getAllCards();
    public List<CardResponseDto> getAllCardsForUser(Long userId);
}
