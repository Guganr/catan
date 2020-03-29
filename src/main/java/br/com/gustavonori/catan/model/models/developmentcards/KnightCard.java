package br.com.gustavonori.catan.model.models.developmentcards;

import br.com.gustavonori.catan.model.services.PlayerService;

public class KnightCard extends DevelopmentCard {

    public KnightCard() {
        this.name = "Cavaleiro";
        this.description = "Esta carta permite ao jogador mover o ladrão e roubar uma carta de matéria prima de um dos " +
                "jogadores que possua construção na região para onde foi movido o ladrão";
        this.points = 0;
        this.picked = false;
        this.flipped = false;
    }

    @Override
    public void specificAction(PlayerService playerService) {

    }
}
