package br.com.gustavonori.catan.model.constructions.builders;

import br.com.gustavonori.catan.model.elements.Elements;

import java.util.Map;

import static br.com.gustavonori.catan.model.elements.Elements.*;

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

}
