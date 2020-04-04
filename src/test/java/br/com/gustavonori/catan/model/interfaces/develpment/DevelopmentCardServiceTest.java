package br.com.gustavonori.catan.model.interfaces.develpment;

import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.models.player.Player;
import br.com.gustavonori.catan.model.services.PlayerService;
import br.com.gustavonori.catan.model.services.DevelopmentCardService;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static br.com.gustavonori.catan.model.models.elements.Elements.*;
import static br.com.gustavonori.catan.model.models.elements.Elements.WOOD;

public class DevelopmentCardServiceTest {

    DevelopmentCardService developmentCardService;
    private PlayerService playerService;

    @Before
    public void setUp(){
        playerService = new PlayerService(new Player(1, "Gustavo"));
        developmentCardService = new DevelopmentCardService();
    }

    public void addElements(Map<Elements, Integer> elements) {
        elements.forEach((name, quantity) -> {
            playerService.receivingElements(name, quantity);
        });
    }

    @Test
    public void getADevelopmentCard() {
        Map<Elements, Integer> elements = new HashMap<>();
        elements.put(BRICK, 2);
        elements.put(WHEAT, 2);
        elements.put(SHEEP, 2);
        elements.put(WOOD, 1);
        addElements(elements);
//        developmentCardService.getADevelopmentCard(playerService, 1);
    }
}