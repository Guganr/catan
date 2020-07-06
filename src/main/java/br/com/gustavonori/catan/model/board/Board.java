package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.board.positions.Edge;
import br.com.gustavonori.catan.model.board.positions.Intersection;
import br.com.gustavonori.catan.model.models.elements.Element;
import br.com.gustavonori.catan.model.services.PlayerService;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collections;
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

    public Edge getEdgeById(int id) {
        for (BoardPosition position : positions) {
            for (Edge edge : position.getPiece().getEdges()) {
                if (edge.getId() == id) {
                    return edge;
                }
            }
        }
        return null;
    }
    public Intersection getIntersectionById(int id) {
        for (BoardPosition position : positions) {
            for (Edge edge : position.getPiece().getEdges()) {
                if (edge.containsIntersectionById(id)) {
                    return edge.getIntersectionById(id);
                }
            }
        }
        return null;
    }

    public List<Piece> getPiecesByEdgeId(int id) {
        List<Piece> pieces = new ArrayList<>();
        for (BoardPosition position : positions) {
            if (position.getPiece().containsEdgeById(id))
                pieces.add(position.getPiece());
            if (pieces.size() == 2)
                break;
        }
        return pieces;
    }

    public List<Piece> getPiecesByEdge(Edge edge) {
        List<Piece> pieces = new ArrayList<>();
        for (BoardPosition position : positions) {
            if (position.getPiece().getEdges().contains(edge))
                pieces.add(position.getPiece());
            if (pieces.size() == 2)
                break;
        }
        return pieces;
    }

    public List<Piece> getPiecesByIntersectionId(int id) {
        List<Piece> pieces = new ArrayList<>();
        for (BoardPosition position : positions) {
            if (position.getPiece().containsIntersectionById(id))
                pieces.add(position.getPiece());
            if (pieces.size() == 3)
                break;
        }
        return pieces;
    }

    public List<Piece> getPiecesByIntersection(Intersection intersection) {
        List<Piece> pieces = new ArrayList<>();
        for (BoardPosition position : positions) {
            if (position.getPiece().containsIntersection(intersection))
                pieces.add(position.getPiece());
            if (pieces.size() == 3)
                break;
        }
        return pieces;
    }

    public List<Element> getElementsByIntersection(Intersection intersection) {
        List<Piece> pieces = getPiecesByIntersection(intersection);
        if (!pieces.isEmpty()) {
            List<Element> elements = new ArrayList<>();
            for (Piece piece : pieces) {
                BoardPosition boardPosition = positions.get(piece.getId());
                elements.add(boardPosition.getElement());
            }
            return elements;
        }
        return Collections.emptyList();
    }
    public boolean isThereASequenceOfRoads(Intersection intersection, PlayerService playerService) {
        List<Piece> pieces = getPiecesByIntersection(intersection);
        List<Edge> commonEdges = getCommonEdges(pieces, intersection, playerService);
        return getNextEdges(commonEdges, intersection, playerService);
    }

    private boolean getNextEdges(List<Edge> commonEdges, Intersection intersection, PlayerService playerService) {
        List<Intersection> intersectionList = new ArrayList<>();
        List<Edge> edgesByIntersection = new ArrayList<>();
        for (Edge edge : commonEdges) {
            for (Intersection commonIntersection : edge.getIntersections()) {
                if (!commonIntersection.equals(intersection))
                    intersectionList.add(commonIntersection);
            }
        }
        if (!intersectionList.isEmpty()) {
            for (Intersection commonIntersection : intersectionList) {
                edgesByIntersection = getEdgesByIntersection(commonIntersection, playerService);
                if (!edgesByIntersection.isEmpty()) {
                    for (Edge commonEdge : commonEdges) {
                        edgesByIntersection.remove(commonEdge);
                    }
                }
            }
        }
        if (edgesByIntersection.isEmpty()) {
            return false;
        }
        List<Edge> playerEdges = playerService.getPlayerEdges();
        return checkIfIsAnotherPlayerArea(intersection, playerEdges, playerService);
    }

    private boolean checkIfIsAnotherPlayerArea(Intersection intersection, List<Edge> playerEdges, PlayerService playerService) {
        List<Edge> edges = getEdgesByIntersection(intersection);
        edges.removeAll(playerEdges);
        if (edges.size() < 2)
            return true;
        for (Edge edge : edges) {
            if (edge.hasRoad()) {
                if (playerService.isAPlayerRoad(edge))
                    return true;
            } else
                return true;
        }
        return false;
    }

    private List<Edge> getEdgesByIntersection(Intersection commonIntersection, PlayerService playerService) {
        List<Piece> pieces = getPiecesByIntersection(commonIntersection);
        return getCommonEdges(pieces, commonIntersection, playerService);
    }

    private List<Edge> getEdgesByIntersection(Intersection commonIntersection) {
        List<Piece> pieces = getPiecesByIntersection(commonIntersection);
        return getCommonEdges(pieces, commonIntersection);
    }

    private List<Edge> getCommonEdges(List<Piece> pieces, Intersection intersection, PlayerService playerService) {
        List<Edge> allEdges = new ArrayList<>();
        for (Piece piece : pieces) {
            piece.getEdgesByIntersection(intersection, allEdges, playerService);
        }
        return allEdges;
    }

    private List<Edge> getCommonEdges(List<Piece> pieces, Intersection intersection) {
        List<Edge> allEdges = new ArrayList<>();
        for (Piece piece : pieces) {
            piece.getEdgesByIntersection(intersection, allEdges);
        }
        return allEdges;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        positions.forEach(p -> {
            sb.append("(").append(p.getPiece().toString()).append(") ");
        });
        return sb.toString();
    }
}
