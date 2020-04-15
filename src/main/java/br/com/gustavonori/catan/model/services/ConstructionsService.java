package br.com.gustavonori.catan.model.services;

import br.com.gustavonori.catan.model.board.BoardBuilder;
import br.com.gustavonori.catan.model.builders.Constructions;
import br.com.gustavonori.catan.model.models.elements.Element;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.models.player.Player;
import br.com.gustavonori.catan.model.models.player.RemovingElementException;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConstructionsService {
    private Constructions constructions;

    public ConstructionsService(Constructions constructions) {
        this.constructions = constructions;
    }

    public void insertElementIntoAPosition(BoardBuilder board, List<PlayerService> playerServiceList, String position) {
        if (constructions.checkPosition(board, playerServiceList, position)) {
            //ADDSUCCESSMSG
            constructions.setPosition(position);
        } else {
            //ADDERRORMSG
        }
    }
}
