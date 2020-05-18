package br.com.gustavonori.catan.model.board.positions;

import br.com.gustavonori.catan.model.builders.CityBuilder;
import br.com.gustavonori.catan.model.builders.Constructions;
import br.com.gustavonori.catan.model.builders.VillageBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;

public class Intersection {
    private int id;
    private boolean inUse;
    private boolean village;
    List<Edge> edges;
    protected int initPosition;

    public Intersection(int id) {
        this.id = id;
        this.inUse = false;
        this.village = false;
        this.edges = new ArrayList<>();
    }

    public Intersection() {
        this.inUse = false;
        this.village = false;
        this.edges = new ArrayList<>();
    }

    public Intersection(int id, List<Edge> edges) {
        this.id = id;
        this.inUse = false;
        this.village = false;
        this.edges = edges;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getInitPosition() {
        return initPosition;
    }

    public void setInitPosition(int initPosition) {
        this.initPosition = initPosition;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public boolean isVillage() {
        return village;
    }

    public void setVillage(boolean village) {
        this.village = village;
    }

    public boolean isAValidPosition(Constructions construction) {
        return construction instanceof CityBuilder || construction instanceof VillageBuilder;
    }

    public Edge getLastEdge() {
        return edges.get(edges.size() - 1);
    }

    public boolean isCompleteOfEdges(){
        return this.edges.size() < 4;
    }

    public List<Edge> getNext() {
        if (edges.isEmpty())
            return emptyList();
        if (!edges.get(1).isConnected())
            return singletonList(edges.get(1));
        if (!edges.get(2).isConnected())
            return singletonList(edges.get(2));
        return emptyList();
    }

    @Override
    public String toString() {
        String text = "I" + this.id + "\n";
        StringBuilder sb = new StringBuilder(text);
        this.edges.forEach(edge -> {
            sb.append("e" + edge.getId() + ", ");
        });
        return sb.toString();
    }
}
