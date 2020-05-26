package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.board.positions.Edge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Piece {
    List<Edge> edges;
    List<Piece> intersectionPieces;

    public Piece() {
        this.edges = new ArrayList<>();
        this.intersectionPieces = new ArrayList<>();
    }

    public Piece(List<Edge> edges) {
        this.edges = edges;
        this.intersectionPieces = new ArrayList<>();
    }

    public Piece(List<Edge> edges, List<Piece> intersectionPieces) {
        this.edges = edges;
        this.intersectionPieces = intersectionPieces;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public List<Piece> getIntersectionPieces() {
        return intersectionPieces;
    }

    public void setIntersectionPieces(List<Piece> intersectionPieces) {
        this.intersectionPieces = intersectionPieces;
    }

    public boolean hasNext() {
        return intersectionPieces.size() < 6;
    }

    public Optional<Edge> getNext() {
        boolean check = true;
        for (Edge edge : edges) {
            for (Piece piece : intersectionPieces) {
                if (piece.getEdges().contains(edge)){
                    check = false;
                    break;
                }
                check = true;
            }
            if (check)
                return Optional.of(edge);
        }
        return Optional.empty();
    }

    public void addNewIntersectionPiece(Piece newPiece) {
        intersectionPieces.add(newPiece);
        newPiece.intersectionPieces.add(this);
    }

    public Optional<Piece> getNextPiece() {
        for (Piece piece : intersectionPieces) {
            if (piece.hasNext())
                return Optional.of(piece);
        }
        return Optional.empty();
    }
}
