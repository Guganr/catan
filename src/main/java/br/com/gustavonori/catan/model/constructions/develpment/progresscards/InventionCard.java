package br.com.gustavonori.catan.model.constructions.develpment.progresscards;

import br.com.gustavonori.catan.model.constructions.develpment.DevelopmentCard;
import br.com.gustavonori.catan.model.player.PlayerService;

public class InventionCard extends DevelopmentCard {
    public InventionCard() {
        this.name = "Invenção";
        this.description = "Esta carta permite ao jogador receber, dias cartas de matéria-prima à sua escolha.";
        this.points = 0;
        this.picked = false;
        this.flipped = false;
    }

    @Override
    public void specificAction(PlayerService playerService) {

    }
}
