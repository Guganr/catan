package br.com.gustavonori.catan.model.constructions.develpment.pointcards;

import br.com.gustavonori.catan.model.constructions.develpment.DevelopmentCard;
import br.com.gustavonori.catan.model.player.PlayerService;

public class PointCard extends DevelopmentCard {
    public PointCard() {
        this.description = "1 ponto";
        this.points = 1;
        this.picked = false;
        this.flipped = false;
    }

    @Override
    public void specificAction(PlayerService playerService) {

    }
}
