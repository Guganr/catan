package br.com.gustavonori.catan.model.constructions.develpment;

import br.com.gustavonori.catan.model.elements.Element;
import br.com.gustavonori.catan.model.elements.Elements;
import br.com.gustavonori.catan.model.player.Player;
import br.com.gustavonori.catan.model.player.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static br.com.gustavonori.catan.model.elements.Elements.*;
import static br.com.gustavonori.catan.model.elements.Elements.WOOD;
import static java.lang.Math.random;
import static org.junit.jupiter.api.Assertions.*;

public class DevelopmentCardServiceTest {

    DevelopmentCardService developmentCardService;
    private Player player;
    private PlayerService playerService;

    @Before
    public void setUp(){
        player = new Player(1, "Gustavo");
        playerService = new PlayerService(player);
        developmentCardService = new DevelopmentCardService();
    }

    public void addElements(Map<Elements, Integer> elements) {
        elements.forEach((name, quantity) -> {
            playerService.receivingElements(player.getElements().get(playerService.getElementIndex(name)), quantity);
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