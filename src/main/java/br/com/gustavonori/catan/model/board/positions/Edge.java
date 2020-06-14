package br.com.gustavonori.catan.model.board.positions;

import br.com.gustavonori.catan.model.builders.RoadBuilder;

import java.util.ArrayList;
import java.util.List;

public class Edge {
    private int id;
    List<Intersection> intersections;
    RoadBuilder road;

    public Edge() {
        this.intersections = new ArrayList<>();
    }

    public Edge(int id) {
        this.id = id;
        this.intersections = new ArrayList<>();
    }

    public Edge(int id, List<Intersection> intersections) {
        this.id = id;
        this.intersections = intersections;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Intersection> getIntersections() {
        return intersections;
    }

    public void setIntersections(List<Intersection> intersections) {
        this.intersections = intersections;
    }

    public RoadBuilder getRoad() {
        return road;
    }

    public void setRoad(RoadBuilder road) {
        this.road = road;
    }

    public boolean isAvailable() {
        return true;
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

    public boolean containsIntersectionById(int id) {
        for (Intersection intersection : intersections) {
            if (intersection.getId() == id)
                return true;
        }
        return false;
    }

    public Intersection getIntersectionById(int id) {
        for (Intersection intersection : intersections) {
            if (intersection.getId() == id)
                return intersection;
        }
        return null;
    }

    public boolean hasRoad() {
        return road != null;
    }
}
