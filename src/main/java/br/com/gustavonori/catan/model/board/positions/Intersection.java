package br.com.gustavonori.catan.model.board.positions;

import br.com.gustavonori.catan.model.builders.CityBuilder;
import br.com.gustavonori.catan.model.builders.Constructions;
import br.com.gustavonori.catan.model.builders.VillageBuilder;

public class Intersection {
    private int id;
    private boolean inUse;
    private boolean village;
    protected int initPosition;

    public Intersection(int id) {
        this.id = id;
        this.inUse = false;
        this.village = false;
    }

    public Intersection() {
        this.inUse = false;
        this.village = false;
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

    public int getInitPosition() {
        return initPosition;
    }

    public void setInitPosition(int initPosition) {
        this.initPosition = initPosition;
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

}
