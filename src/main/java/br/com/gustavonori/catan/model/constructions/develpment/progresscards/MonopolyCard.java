package br.com.gustavonori.catan.model.constructions.develpment.progresscards;

import br.com.gustavonori.catan.model.constructions.develpment.DevelopmentCard;
import br.com.gustavonori.catan.model.player.PlayerService;

public class MonopolyCard extends DevelopmentCard {

    public MonopolyCard() {
        this.name = "Monopólio";
        this.description = "Esta carta permite ao jogador escolher uma matéria-prima. Todos os outros jogadores " +
                "têm de lhe entregar todas as cartas que possuam da matéria-prima escolhida.";
        this.points = 0;
        this.picked = false;
        this.flipped = false;
    }
    @Override
    public void specificAction(PlayerService playerService) {

    }
}
