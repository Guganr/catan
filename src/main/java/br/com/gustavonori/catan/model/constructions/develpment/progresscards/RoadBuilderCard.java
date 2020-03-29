package br.com.gustavonori.catan.model.constructions.develpment.progresscards;

import br.com.gustavonori.catan.model.constructions.builders.RoadBuilder;
import br.com.gustavonori.catan.model.constructions.develpment.DevelopmentCard;
import br.com.gustavonori.catan.model.player.PlayerService;

public class RoadBuilderCard extends DevelopmentCard {
    public RoadBuilderCard() {
        this.name = "Construção de estradas";
        this.description = "Esta carta permite ao jogador construir gratuitamente duas estradas";
        this.points = 0;
        this.picked = false;
        this.flipped = false;
    }

    @Override
    public void specificAction(PlayerService playerService) {
        for (int i = 0; i < 2; i++)
            playerService.getPlayer().getConstructions().add(new RoadBuilder());
    }
}
