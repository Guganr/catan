package br.com.gustavonori.catan.model.board.positions;

import java.util.ArrayList;
import java.util.List;

public class Edge {
    private int id;
    private boolean inUse;
    List<Intersection> intersections;
    private boolean connected;

    public Edge() {
        this.intersections = new ArrayList<>();
    }

    public Edge(int id) {
        this.id = id;
        inUse = false;
        this.intersections = new ArrayList<>();
    }

    public Edge(int id, List<Intersection> intersections) {
        this.id = id;
        this.inUse = false;
        this.intersections = intersections;
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

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public List<Intersection> getIntersections() {
        return intersections;
    }

    public void setIntersections(List<Intersection> intersections) {
        this.intersections = intersections;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("E" + id + " - ");
        intersections.forEach(intersection -> {
            sb.append("I" + intersection.getId() + " ");
        });
        return sb.toString();
    }

    public Intersection getLastIntersection() {
        return intersections.get(intersections.size() - 1);
    }
}
