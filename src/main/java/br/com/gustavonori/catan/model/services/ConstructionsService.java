package br.com.gustavonori.catan.model.services;

import br.com.gustavonori.catan.model.board.BoardBuilder;
import br.com.gustavonori.catan.model.builders.Constructions;

import java.util.List;

public class ConstructionsService {
    private Constructions constructions;

    public ConstructionsService(Constructions constructions) {
        this.constructions = constructions;
    }

    public void insertElementIntoAPosition(BoardBuilder board, List<PlayerService> playerServiceList) {
//        if (constructions.checkPosition(board, playerServiceList, "position")) {
//            //ADDSUCCESSMSG
//            constructions.setPosition(position);
//        } else {
//            //ADDERRORMSG
//        }
    }
}
