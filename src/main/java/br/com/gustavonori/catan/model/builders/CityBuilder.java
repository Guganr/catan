package br.com.gustavonori.catan.model.builders;

import br.com.gustavonori.catan.model.board.BoardBuilder;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.services.PlayerService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static br.com.gustavonori.catan.model.models.elements.Elements.*;

public class CityBuilder extends Constructions {

    private static final String NAME = "CITY";
    private static final int POINTS = 1;
    public static Map<Elements, Integer> elementsToBuild = (Map.of(
            WHEAT,2,
            ROCK,3
    ));

    public CityBuilder() {
        super(NAME, POINTS, elementsToBuild);
    }


    @Override
    public boolean checkPosition(BoardBuilder board, List<PlayerService> playerServiceList, String position){
        AtomicBoolean check = new AtomicBoolean(false);
        playerServiceList.get(0).getPlayer().getConstructions().forEach(constructions -> {
            if (constructions instanceof VillageBuilder && constructions.getPosition().equals(position))
                check.set(true);
        });
        //ADDERRORMSG
        return check.get();
    }
}
