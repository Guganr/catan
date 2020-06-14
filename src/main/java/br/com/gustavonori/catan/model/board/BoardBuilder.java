package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.board.positions.Edge;
import br.com.gustavonori.catan.model.board.positions.Intersection;
import br.com.gustavonori.catan.model.models.elements.Element;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.thief.Desert;

import java.util.*;

import static br.com.gustavonori.catan.model.models.elements.Elements.*;

public class BoardBuilder {
    private Map<Integer, String> alphabet;
    private Map<Integer, String> initPositionsOnTheBoard;
    private Board board;
    public BoardBuilder(Board board) {
        this.board = new Board();
    }

    public Board getBoard(){
        return this.board;
    }

    public void start() {
        Piece firstPiece = createFirstPiece();
        BoardPosition boardPosition = new BoardPosition(firstPiece);
        boardPosition.setElement(new Element(DESERT));
        board.getPositions().add(boardPosition);
        createBoard(firstPiece);
        populateElements(board);
    }

    private void populateElements(Board board) {
        List<Element> elements = populateElements();
        for (Element element : elements) {
            for (BoardPosition position : board.getPositions()) {
                if (position.getElement() == null) {
                    position.setElement(element);
                    break;
                }
            }
        }
    }

    public Piece createFirstPiece() {
        List<Edge> edges = new ArrayList<>();
        List<Intersection> allIntersections = new ArrayList<>();
        Intersection lastIntersection = new Intersection();
        int intersectionsId = 1;
        for (int i = 1; i < 7; i++) {
            Edge edge = new Edge(i);
            if (i == 1) {
                Intersection intersection = new Intersection(intersectionsId);
                edge.getIntersections().add(intersection);
                allIntersections.add(intersection);
            } else {
                edge.getIntersections().add(lastIntersection);
            }
            if (i < 6) {
                lastIntersection = new Intersection(++intersectionsId);
                edge.getIntersections().add(lastIntersection);
                allIntersections.add(lastIntersection);
            } else {
                edge.getIntersections().add(allIntersections.get(0));
            }
            edges.add(edge);
        }
        return new Piece(edges);
    }

    private void createBoard(Piece firstPiece) {
        int idPiece = 1;
        Piece pieceInUse;
        List<Edge> allEdges = new ArrayList<>();
        Optional<Piece> nextPiece = Optional.ofNullable(firstPiece);
        while (nextPiece.isPresent() && idPiece < 19) {
            pieceInUse = nextPiece.get();
            addAllEdges(allEdges, pieceInUse.getEdges());
            while (pieceInUse.hasNext()) {
                Optional<Edge> edgeIntersectionOptional = pieceInUse.getNext();
                Edge lastEdge = getLastEdge(allEdges);
                if (edgeIntersectionOptional.isPresent()) {
                    Piece newPiece = new Piece();
                    List<Edge> edges = new ArrayList<>();
                    Edge edgeIntersection = edgeIntersectionOptional.get();
                    Intersection lastEdgeIntersection = edgeIntersection.getIntersections().get(0);
                    Intersection lastIntersection = getLastIntersection(allEdges);
                    newPiece.setId(idPiece);
                    edges.add(edgeIntersection);
                    Intersection newIntersection = new Intersection();
                    Edge newEdge = new Edge();
                    if (pieceInUse.getIntersectionPieces().size() > 0) {
                        Edge commonEdge = getCommonEdge(edges, pieceInUse);
                        edges.add(commonEdge);
                    } else {
                        newEdge = new Edge(lastEdge.getId() + 1);
                        newEdge.getIntersections().add(lastEdgeIntersection);
                        newIntersection = new Intersection(lastIntersection.getId() + 1);
                        newEdge.getIntersections().add(newIntersection);
                        edges.add(newEdge);
                        lastEdge = newEdge;
                        lastIntersection = newIntersection;
                    }
                    addAllEdges(allEdges, pieceInUse.getEdges());
                    while (edges.size() < 6) {
                        newEdge = new Edge(lastEdge.getId() + 1);
                        newEdge.getIntersections().add(lastIntersection);

                        if (edges.size() == 5) {
                            if (JoinLastPieces(pieceInUse, lastEdge, newPiece, edges, newEdge))
                                break;
                        } else {
                            if (isTheLastPieceFromTheLoop(pieceInUse, edges)) {
                                newIntersection = new Intersection(lastIntersection.getId() + 1);
                                newEdge.getIntersections().add(newIntersection);
                            }
                        }
                        edges.add(newEdge);
                        addAllEdges(allEdges, edges);
                        lastEdge = newEdge;
                        lastIntersection = newIntersection;
                    }
                    createNewBorderPosition(pieceInUse, newPiece, edges);
                    idPiece++;
                }
            }
            pieceInUse.connectFirstAndLastIntersectionPieces();
            nextPiece = pieceInUse.getNextPiece();
        }
    }

    private boolean isTheLastPieceFromTheLoop(Piece pieceInUse, List<Edge> edges) {
        return pieceInUse.getIntersectionPieces().size() < 5 || edges.size() != 4;
    }

    private void createNewBorderPosition(Piece pieceInUse, Piece newPiece, List<Edge> edges) {
        newPiece.getEdges().addAll(edges);
        pieceInUse.addNewIntersectionPiece(newPiece);
        board.getPositions().add(new BoardPosition(newPiece));
    }

    private boolean JoinLastPieces(Piece pieceInUse, Edge lastEdge, Piece newPiece, List<Edge> edges, Edge newEdge) {
        Intersection lastEdgeIntersection;
        if (pieceInUse.getIntersectionPieces().size() == 5) {
            if (pieceInUse.joinFistAndLastPiece(lastEdge, newPiece, edges))
                return true;
        } else {
            lastEdgeIntersection = edges.get(0).getIntersections().get(1);
            newEdge.getIntersections().add(lastEdgeIntersection);
        }
        return false;
    }

    private Edge getCommonEdge(List<Edge> edges, Piece pieceInUse) {
        int position = 1;
        Piece piece = pieceInUse.getIntersectionPieces().get(pieceInUse.getIntersectionPieces().size() - 1);
        Edge edge = piece.getEdges().get(piece.getEdges().size() - position);
        while (isEdgeAvailable(edge)) {
            position++;
            edge = piece.getEdges().get(piece.getEdges().size() - position);
        }
        return edge;
    }

    private boolean isEdgeAvailable(Edge edge) {
        int i = 0;
        for (BoardPosition boardPosition : board.getPositions()) {
           if (boardPosition.getPiece().getEdges().contains(edge))
               i++;
        }
        return i > 1;
    }

    private Intersection getLastIntersection(List<Edge> allEdges) {
        Intersection lastIntersection = new Intersection();
        for (Edge edge : allEdges) {
            for (Intersection intersection : edge.getIntersections()) {
                if (intersection.getId() > lastIntersection.getId())
                    lastIntersection = intersection;
            }
        }
        return lastIntersection;
    }

    private void addAllEdges(List<Edge> allEdges, List<Edge> edges) {
        for (Edge edge : edges) {
            if (!allEdges.contains(edge))
                allEdges.add(edge);
        }
    }

    private Edge getLastEdge(List<Edge> allEdges) {
        Edge lastEdge = new Edge();
        for (Edge edge : allEdges) {
            if (edge.getId() > lastEdge.getId())
                lastEdge = edge;
        }
        return lastEdge;
    }

    public void distributingNumbers() {
        List<Integer> numbers = new ArrayList<>(List.of(2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12));
        Collections.shuffle(numbers);
        int i = 0;
        for (BoardPosition boardPosition : board.getPositions()) {
            if (!boardPosition.getElement().getName().equals(DESERT))
                boardPosition.setNumber(numbers.get(i));
            else
                numbers.add(numbers.get(i));
            i++;
        }
    }

    public List<Element> populateElements() {
        List<Element> elementsList = new ArrayList<>();
        Map<Elements, Integer> elementsMap = new HashMap<>();
        elementsMap.put(WHEAT, 4);
        elementsMap.put(WOOD, 4);
        elementsMap.put(SHEEP, 4);
        elementsMap.put(ROCK, 3);
        elementsMap.put(BRICK, 3);
        elementsMap.forEach((element, qty) -> {
            for (int i = 0; i < qty; i++) {
                elementsList.add(new Element(element));
            }
        });

        Collections.shuffle(elementsList);
        return elementsList;
    }
    public List<String> getMapping(){
        List<String> map = new ArrayList<>();
        board.getPositions().forEach((position) -> {
            position.getMapping().forEach((key, value) -> {
                map.add(value);
            });
        });
        return map;
    }
}
