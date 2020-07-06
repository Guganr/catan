package br.com.gustavonori.catan.model.builders;

import br.com.gustavonori.catan.model.board.positions.Edge;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.services.PlayerService;

import java.util.Map;

import static br.com.gustavonori.catan.model.models.elements.Elements.BRICK;
import static br.com.gustavonori.catan.model.models.elements.Elements.WOOD;

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


    public void build(Edge edge, PlayerService playerService) {
        if (edge.isAvailable()) {
            addRoadIntoEdge(edge);
            this.build(playerService);
        } else {
            //ADDERRORMSG
            System.out.println("Estrada ocupada.");
        }
    }

    private void addRoadIntoEdge(Edge edge) {
        edge.setRoad(this);
        setEdge(edge);
    }

}
