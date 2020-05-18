package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.board.parts.Piece;
import br.com.gustavonori.catan.model.board.positions.Intersection;
import org.springframework.context.annotation.Bean;

import java.util.*;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        positions.forEach(boardPosition -> {
            boardPosition.getPieces().forEach(piece -> {
                sb.append(piece.getId() + "\n" + piece.getIntersection().toString());
            });
        });
        return sb.toString();
    }
}
