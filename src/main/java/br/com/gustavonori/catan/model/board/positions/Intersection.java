package br.com.gustavonori.catan.model.board.positions;

import br.com.gustavonori.catan.model.builders.BuildPoints;

public class Intersection {
    private int id;
    BuildPoints build;

    public Intersection(int id) {
        this.id = id;
    }

    public Intersection() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BuildPoints getBuild() {
        return build;
    }

    public void setBuild(BuildPoints build) {
        this.build = build;
    }

    public boolean isAvailable() {
        return !hasBuild();
    }

    public boolean hasBuild() {
        return build != null;
    }

}
