package br.com.gustavonori.catan.model.builders;

import br.com.gustavonori.catan.model.board.BoardBuilder;
import br.com.gustavonori.catan.model.board.positions.Edge;
import br.com.gustavonori.catan.model.board.positions.Intersection;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.services.PlayerService;

import java.util.List;
import java.util.Map;

import static br.com.gustavonori.catan.model.models.elements.Elements.*;
import static java.lang.Integer.parseInt;

public class VillageBuilder extends BuildPoints {

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


    public void build(Intersection intersection, PlayerService playerService) {
        if (intersection.isAvailable()) {
            addBuildIntoIntersection(intersection);
            this.build(playerService);
        } else {
            //ADDERRORMSG
        }
    }

    public CityBuilder createCity() {
        CityBuilder cityBuilder = new CityBuilder();
        cityBuilder.setIntersection(getIntersection());
        return cityBuilder;
    }
}
