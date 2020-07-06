package br.com.gustavonori.catan.model.builders;

import br.com.gustavonori.catan.model.board.BoardBuilder;
import br.com.gustavonori.catan.model.board.positions.Intersection;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.services.PlayerService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static br.com.gustavonori.catan.model.models.elements.Elements.*;

public class CityBuilder extends BuildPoints {

    private static final String NAME = "CITY";
    private static final int POINTS = 2;
    public static final Map<Elements, Integer> elementsToBuild = (Map.of(
            WHEAT,2,
            ROCK,3
    ));

    public CityBuilder() {
        super(NAME, POINTS, elementsToBuild);
    }

    public void build(PlayerService playerService) {
        Intersection intersection = getIntersection();
        if (intersection.isAvailable()) {
            addBuildIntoIntersection(intersection);
            this.build(playerService);
        } else {
            //ADDERRORMSG
        }
    }
}
