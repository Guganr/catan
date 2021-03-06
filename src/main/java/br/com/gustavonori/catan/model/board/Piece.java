package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.board.positions.Edge;
import br.com.gustavonori.catan.model.board.positions.Intersection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Piece {
    private int id;
    private List<Edge> edges;
    private List<Piece> intersectionPieces;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Piece getNextIntersectionPieceAvailable() {
        for (Piece piece : intersectionPieces) {
            if (piece.getIntersectionPieces().size() < 6)
                return piece;
        }
        return this.getIntersectionPieces().get(0);
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
        if (intersectionPieces.size() > 1) {
            Piece lastIntersectionPiece = intersectionPieces.get(intersectionPieces.size() - 2);
            newPiece.intersectionPieces.add(lastIntersectionPiece);
            lastIntersectionPiece.intersectionPieces.add(newPiece);
        }
    }

    public Optional<Piece> getNextPiece() {
        for (Piece piece : intersectionPieces) {
            if (piece.hasNext())
                return Optional.of(piece);
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Piece").append(id);
        edges.forEach(e -> {
            sb.append("(").append(e.toString()).append(") ");
        });
        return sb.toString();
    }

    public void connectFirstAndLastIntersectionPieces() {
        if (hasNext()) {
            Piece firstIntersection = intersectionPieces.get(0);
            Piece lastIntersection = intersectionPieces.get(intersectionPieces.size() - 1);
            firstIntersection.intersectionPieces.add(lastIntersection);
            lastIntersection.intersectionPieces.add(firstIntersection);
        }
    }

    public boolean joinFistAndLastPiece(Edge lastEdge, Piece newPiece, List<Edge> edges) {
        Piece firstPieceForJoin = this.getNextIntersectionPieceAvailable();
        Optional<Edge> nextEdgeFirstIntOptional = firstPieceForJoin.getNext();
        if (nextEdgeFirstIntOptional.isPresent()) {
            Edge nextEdgeFirstInt = nextEdgeFirstIntOptional.get();
            edges.get(edges.size() - 1).getIntersections().add(nextEdgeFirstInt.getLastIntersection());
            edges.add(nextEdgeFirstInt);
            if (lastEdge.getIntersections().size() < 2) {
                lastEdge.getIntersections().add(nextEdgeFirstInt.getLastIntersection());
            }
            firstPieceForJoin.getIntersectionPieces().add(newPiece);
            newPiece.getIntersectionPieces().add(firstPieceForJoin);
            return true;
        }
        return false;
    }
}
