package br.com.gustavonori.catan.model.board;

import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {
    private List<BoardPosition> positions;

    public Board() {
        this.positions = new ArrayList<>();
    }

    public List<BoardPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<BoardPosition> positions) {
        this.positions = positions;
    }
}
