package br.com.gustavonori.catan.model.builders;

import br.com.gustavonori.catan.model.models.elements.Elements;

import java.util.Map;

public class BuildPoints extends Constructions {
    public BuildPoints(String name, int points, Map<Elements, Integer> elementsToBuild) {
        super(name, points, elementsToBuild);
    }
}
