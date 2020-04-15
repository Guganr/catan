package br.com.gustavonori.catan.model.builders;

import br.com.gustavonori.catan.model.board.Board;
import br.com.gustavonori.catan.model.board.BoardBuilder;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.services.PlayerService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static br.com.gustavonori.catan.model.models.elements.Elements.BRICK;
import static br.com.gustavonori.catan.model.models.elements.Elements.WOOD;
import static java.lang.Integer.parseInt;

public class RoadBuilder extends Constructions {

    private static final String NAME = "ROAD";
    private static final int POINTS = 0;
    public static Map<Elements, Integer> elementsToBuild = (Map.of(
            BRICK,1,
            WOOD,1
    ));

    public RoadBuilder() {
        super(NAME, POINTS, elementsToBuild);
    }

    @Override
    public boolean checkPosition(BoardBuilder board, List<PlayerService> playerServiceList, String position){
        if (isRoadPosition(position)) {
           return super.checkPosition(board, playerServiceList, position);
        } else {
            //ADDERRORMSG
            return false;
        }
    }
}
