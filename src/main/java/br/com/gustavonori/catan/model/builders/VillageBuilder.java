package br.com.gustavonori.catan.model.builders;

import br.com.gustavonori.catan.model.models.elements.Elements;

import java.util.Map;

import static br.com.gustavonori.catan.model.models.elements.Elements.*;

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

}
