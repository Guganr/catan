package br.com.gustavonori.catan.model.board.positions;

import java.util.List;
import java.util.Optional;

public class Edge {
    private int id;
    private boolean inUse;
    private boolean connected;

    public Edge() {}

    public Edge(int id) {
        this.id = id;
        inUse = false;
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
    @Override
    public String toString() {
        return "e" + id;
    }
}
