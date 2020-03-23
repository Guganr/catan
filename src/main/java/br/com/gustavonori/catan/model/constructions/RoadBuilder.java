package br.com.gustavonori.catan.model.constructions;

import br.com.gustavonori.catan.model.elements.Elements;

import java.util.Map;

import static br.com.gustavonori.catan.model.elements.Elements.BRICK;
import static br.com.gustavonori.catan.model.elements.Elements.WOOD;

public class RoadBuilder extends Constructions {

    private static final String ROAD = "ROAD";
    private static final int POINTS = 0;
    public static Map<Elements, Integer> elementsToBuild = (Map.of(
            BRICK,1,
            WOOD,1
    ));

    public RoadBuilder() {
        super(ROAD, POINTS, elementsToBuild);
    }

}
