package br.com.gustavonori.catan.model.builders;

import br.com.gustavonori.catan.model.board.BoardBuilder;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.services.PlayerService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static br.com.gustavonori.catan.model.models.elements.Elements.*;
import static java.lang.Integer.parseInt;

public class VillageBuilder extends Constructions {

    private static final String NAME = "VILLAGE";
    private static final int POINTS = 1;
    public static Map<Elements, Integer> elementsToBuild = (Map.of(
            BRICK,1,
            SHEEP,1,
            WHEAT,1,
            WOOD,1
    ));

    public VillageBuilder() {
        super(NAME, POINTS, elementsToBuild);
    }

    @Override
    public boolean checkPosition(BoardBuilder board, List<PlayerService> playerServiceList, String position){
        if (!isRoadPosition(position)) {
            return super.checkPosition(board, playerServiceList, position);
        } else {
            return false;
        }
    }
}
