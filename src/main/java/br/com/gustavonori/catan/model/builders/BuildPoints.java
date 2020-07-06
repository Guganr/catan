package br.com.gustavonori.catan.model.builders;

import br.com.gustavonori.catan.model.board.positions.Intersection;
import br.com.gustavonori.catan.model.models.elements.Elements;

import java.util.Map;

public class BuildPoints extends Constructions {
    private Intersection intersection;

    public BuildPoints(String name, int points, Map<Elements, Integer> elementsToBuild) {
        super(name, points, elementsToBuild);
    }
    public Intersection getIntersection() {
        return intersection;
    }

    public void setIntersection(Intersection intersection) {
        this.intersection = intersection;
    }

    protected void addBuildIntoIntersection(Intersection intersection) {
        intersection.setBuild(this);
        setIntersection(intersection);
    }
}
