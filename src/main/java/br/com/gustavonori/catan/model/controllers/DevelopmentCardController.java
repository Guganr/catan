package br.com.gustavonori.catan.model.controllers;

import br.com.gustavonori.catan.model.services.DevelopmentCardService;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.models.developmentcards.DevelopmentCard;
import br.com.gustavonori.catan.model.models.player.Player;
import br.com.gustavonori.catan.model.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.gustavonori.catan.model.models.elements.Elements.*;

@RestController
public class DevelopmentCardController {

    @Autowired
    DevelopmentCardService developmentCardService;

    @RequestMapping("/construir")
    public List<DevelopmentCard> listar(){

        Player player = new Player(1, "Gustavo");
        Map<Elements, Integer> elements = new HashMap<>();
        elements.put(SHEEP, 6);
        elements.put(ROCK, 5);
        elements.put(WHEAT, 5);
        PlayerService playerService = new PlayerService(player);
        elements.forEach(playerService::receivingElements);

        if (developmentCardService.newDevelopmentCard(playerService))
            return playerService.getPlayer().getDevelopmentCards();
        else
            return Collections.emptyList();
    }


}
