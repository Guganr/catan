package br.com.gustavonori.catan.model.models.developmentcards.pointcards;

import br.com.gustavonori.catan.model.models.developmentcards.DevelopmentCard;
import br.com.gustavonori.catan.model.services.PlayerService;

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
