package br.com.gustavonori.catan.model.models.developmentcards;

import br.com.gustavonori.catan.model.builders.RoadBuilder;
import br.com.gustavonori.catan.model.models.developmentcards.DevelopmentCard;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.services.PlayerService;

import java.util.Map;

public class RoadBuilderCard extends DevelopmentCard {
    public RoadBuilderCard() {
        this.name = "Construção de estradas";
        this.description = "Esta carta permite ao jogador construir gratuitamente duas estradas";
        this.points = 0;
        this.picked = false;
        this.flipped = false;
    }

    @Override
    public void specificAction(PlayerService playerService, Map<Elements, Integer> elements) {
        if (checkIfPlayerHasTheCard(playerService.getPlayer())) {
            for (int i = 0; i < 2; i++)
                playerService.getPlayer().getConstructions().add(new RoadBuilder());
        } else {
            System.out.println("O jogador não possui esta carta");
        }
    }
}
