package br.com.gustavonori.catan.model.board.positions;

import java.util.List;
import java.util.Optional;

public abstract class Positions {
    private String position;
    private boolean inUse;

    public Positions() {}

    public Positions(String position) {
        this.position = position;
        inUse = false;
    }

    public String getPositions() {
        return position;
    }

    public void setId(String id) {
        this.position = id;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public Optional<Positions> getByPosition(List<Positions> positions, String position) {
        return positions.stream().filter(p -> p.getPositions().equals(position)).findFirst();
    }

}
