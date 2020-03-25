package br.com.gustavonori.catan.model.constructions.develpment;

import br.com.gustavonori.catan.model.elements.Elements;
import br.com.gustavonori.catan.model.player.Player;
import br.com.gustavonori.catan.model.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.gustavonori.catan.model.elements.Elements.BRICK;
import static br.com.gustavonori.catan.model.elements.Elements.WOOD;

@RestController
public class DevelopmentCardController {

    @Autowired
    DevelopmentCardService developmentCardService;

    @RequestMapping("/cartas")
    public List<DevelopmentCard> lista(){
        return developmentCardService.listAll();
    }

    @RequestMapping("/construir")
    public DevelopmentCard listra(){

        Player player = new Player(1, "Gustavo");
        Map<Elements, Integer> elements = new HashMap<>();
        elements.put(BRICK, 2);
        elements.put(WOOD, 1);
        PlayerService playerService = new PlayerService(player);
        elements.forEach((name, quantity) -> {
            playerService.receivingElements(player.getElements().get(playerService.getElementIndex(name)), quantity);
        });
        return developmentCardService.getADevelopmentCard(playerService);
    }


}
