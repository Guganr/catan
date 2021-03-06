package br.com.gustavonori.catan.model.models.developmentcards;

import br.com.gustavonori.catan.model.models.developmentcards.DevelopmentCard;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.services.PlayerService;

import java.util.Map;

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
    public void specificAction(PlayerService playerService, Map<Elements, Integer> elements) {

    }
}
