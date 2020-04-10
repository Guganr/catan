package br.com.gustavonori.catan.model.models.developmentcards.pointcards;

import br.com.gustavonori.catan.model.models.developmentcards.DevelopmentCard;

public class PointCard extends DevelopmentCard {
    public PointCard() {
        this.description = "1 ponto";
        this.points = 1;
        this.picked = false;
        this.flipped = false;
    }
}
