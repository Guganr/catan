package br.com.gustavonori.catan.model.models.developmentcards.progresscards;

import br.com.gustavonori.catan.model.builders.RoadBuilder;
import br.com.gustavonori.catan.model.models.developmentcards.DevelopmentCard;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.services.PlayerService;

import java.util.Map;

public class InventionCard extends DevelopmentCard {
    public InventionCard() {
        this.name = "Invenção";
        this.description = "Esta carta permite ao jogador receber, dias cartas de matéria-prima à sua escolha.";
        this.points = 0;
        this.picked = false;
        this.flipped = false;
    }

    @Override
    public void specificAction(PlayerService playerService, Map<Elements, Integer> elements) {
        if (checkIfPlayerHasTheCard(playerService.getPlayer())) {
            elements.forEach(playerService::receivingElements);
        } else {
            System.out.println("O jogador não possui esta carta");
        }
    }
}
