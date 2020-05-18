package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.board.parts.FirstPiece;
import br.com.gustavonori.catan.model.board.parts.Piece;
import br.com.gustavonori.catan.model.board.positions.Edge;
import br.com.gustavonori.catan.model.board.positions.Intersection;
import br.com.gustavonori.catan.model.models.elements.Element;
import br.com.gustavonori.catan.model.models.elements.Elements;

import javax.management.AttributeList;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

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

    public List<String> getMapping(){
        List<String> map = new ArrayList<>();
        board.getPositions().forEach((position) -> {
            position.getMapping().forEach((key, value) -> {
                map.add(value);
            });
        });
        return map;
    }

    public void putYourHandsUp() {
        Piece piece = null;
        if (board.getPositions().isEmpty()) {
            piece = createFirstPositions();
        }
        board.getPositions().add(new BoardPosition(piece));
        List<Intersection> intersection = piece.getIntersection();
        build(piece);
    }

    public List<Intersection> getIntersectionsToBuild(Piece piece) {
        int init = piece.getInitial();
        Intersection intersectionVol;
        List<Intersection> pieceIntersections = piece.getIntersection();
        List<Intersection> intersectionList = new ArrayList<>();
        Iterator<Intersection> iterator = pieceIntersections.iterator();
        while(iterator.hasNext()) {
            if (iterator.next() == pieceIntersections.get(init)) {
                intersectionVol = pieceIntersections.get(init);
                intersectionVol.setInitPosition(getInitIntersectionPieceParent(init, piece.getIntersectionPieces().size()));
                intersectionList.add(intersectionVol);
                if (iterator.hasNext()) {
                    intersectionVol = iterator.next();
                    init = pieceIntersections.indexOf(intersectionVol);
                    intersectionVol.setInitPosition(
                            getInitIntersectionPieceParent(init, piece.getIntersectionPieces().size())
                    );
                    intersectionList.add(intersectionVol);
                }
            }
        }
        if (piece.getIntersectionPieces().size() > 0) {
            Piece lastPiece = piece.getIntersectionPieces()
                    .get(piece.getIntersectionPieces().size() - 1);
            Iterator<Intersection> lastPieceIterator = lastPiece
                    .getIntersection().iterator();

            Intersection previous = new Intersection();
            while(lastPieceIterator.hasNext()) {
                Intersection next = lastPieceIterator.next();
                for (Intersection intersection : intersectionList) {
                    if (intersection == next) {
                        int position = lastPiece.getIntersection().indexOf(previous);
                        if (position == -1) {
                            position = lastPiece.getIntersection().size() - 1;
                            previous = lastPiece.getIntersection().get(position);
                        }
                        previous.setInitPosition(
                                getInitIntersectionPieceChild(position, piece.getIntersectionPieces().size())
                        );
                        intersectionList.add(previous);
                        return intersectionList;
                    }
                }
                previous = next;
            }
        }
        return intersectionList;
    }

    private int getInitIntersectionPieceParent(int initial, int intersection) {
        if (intersection == 0) {
            switch (initial) {
                case 2:
                    return 0;
                case 3:
                    return 5;
            }
        }
        if (intersection == 1) {
            switch (initial) {
                case 3:
                    return 1;
                case 4:
                    return 0;
            }
        }
        if (intersection == 2) {
            switch (initial) {
                case 4:
                    return 2;
                case 5:
                    return 1;
            }
        }
        return 0;
    }
    private int getInitIntersectionPieceChild(int initial, int intersection) {
        if (intersection == 1){
            switch (initial) {
                case 4:
                    return 2;
                case 5:
                    return 1;
            }
        }
        if (intersection == 2){
            switch (initial) {
                case 0:
                    return 2;
                case 5:
                    return 3;
            }
        }
        return 0;
    }


    private Edge getIntersectionEdge(List<Intersection> intersection) {
        return new Edge(99);
    }

    public void buildForTest(Piece piece) {
        build(piece);
    }

    private void build(Piece piece) {
        Piece pieceForBuild = piece;
        Piece lastPiece = pieceForBuild;
        Piece actualPiece = pieceForBuild.createNewPieceFromLastOne();
        List<Intersection> allIntersections = new ArrayList<>();
        allIntersections.addAll(piece.getIntersection());
        do {
            while (pieceForBuild.hasNext()) {
                List<Intersection> intersectionsToBuild = getIntersectionsToBuild(pieceForBuild);
                List<Intersection> intersectionList = new ArrayList<>();
                Intersection intersection;
                Edge lastEdge = new Edge();
                for (int i = 0; i < 6; i++) {
                    List<Intersection> intersectionsToBuildPosition = getIntersectionsToBuildPosition(intersectionsToBuild, i);
                    List<Edge> edgeList = new ArrayList<>();
                    lastEdge = getLastEdge(allIntersections);
                    if (!intersectionsToBuildPosition.isEmpty() ){
                        intersection = intersectionsToBuildPosition.get(0);
                        if(intersection.getInitPosition() == 5) {
                            intersection.getEdges().add(lastEdge);
                            intersectionList.add(intersection);
                            break;
                        }
                        if(intersection.getInitPosition() == 1) {
                            if (checkIfEdgeMustBeAdded(intersection, pieceForBuild)) {
                                intersection.getEdges().add(lastEdge);
                                lastEdge = new Edge(1 + lastEdge.getId());
                            }
                        }
                    } else {
                        intersection = new Intersection(getLastIntersection(allIntersections).getId() + 1);
                        //Adicionar I14 no 4° pedaço, se não, ele adicionará estrada que não há conexão
                        if (i > 0)
                            edgeList.add(lastEdge);
                    }
                    if (checkIfEdgeMustBeAdded(intersection, pieceForBuild)) {
                        lastEdge = new Edge(1 + lastEdge.getId());
                        edgeList.add(lastEdge);
                    }
                    intersection.getEdges().addAll(edgeList);
                    intersectionList.add(intersection);
                    allIntersectionsAdd(allIntersections, intersection);
                }
                connectLastEdgeAndFirstIntersection(intersectionList, lastEdge);
                createNewBoardPosition(pieceForBuild, actualPiece, intersectionList);
                lastPiece = actualPiece;
                actualPiece = lastPiece.createNewPieceFromLastOne();
            }
        } while (false );
    }

    private void connectLastEdgeAndFirstIntersection(List<Intersection> intersectionList, Edge lastEdge) {
        Intersection firstIntersection = intersectionList.get(0);
        if (firstIntersection.getEdges().size() < 3) {
            if (!firstIntersection.getEdges().contains(lastEdge) )
                firstIntersection.getEdges().add(lastEdge);
        }
    }

    private boolean checkIfEdgeMustBeAdded(Intersection intersection, Piece lastPiece) {
        if (intersection.getEdges().isEmpty())
            return true;
        if (intersection.getEdges().size() == 3)
            return false;
        if (lastPiece.getIntersectionPieces().size() > 0)
            return intersection.getInitPosition() != 0;
        return true;
    }

    private void createNewBoardPosition(Piece lastPiece, Piece actualPiece, List<Intersection> intersectionList) {
        actualPiece.setIntersection(intersectionList);
        lastPiece.getIntersectionPieces().add(actualPiece);
        board.getPositions().add(new BoardPosition(actualPiece));
    }

    private void allIntersectionsAdd(List<Intersection> allIntersections, Intersection intersection) {
        for (Intersection intersection1 : allIntersections) {
            if (intersection.equals(intersection1))
                return;
        }
        allIntersections.add(intersection);
    }

    private List<Intersection> getIntersectionsToBuildPosition(List<Intersection> intersectionsToBuild, int i) {
        for (Intersection intersection : intersectionsToBuild) {
            if (intersection.getInitPosition() == i)
                return Collections.singletonList(intersection);
        }
        return Collections.emptyList();
    }


    public Piece createFirstPositions() {
        List<Intersection> intersectionList = new ArrayList<>();
        List<Edge> firstEdge = new ArrayList<>();
        firstEdge.add(new Edge(1));
        Intersection firstIntersection = new Intersection(1,firstEdge);
        intersectionList.add(firstIntersection);
        int newId = 0;
        do {
            Intersection lastIntersection = getLastIntersection(intersectionList);
            Edge lastEdge = lastIntersection.getLastEdge();

            List<Edge> edges = new ArrayList<>();
            lastEdge.setConnected(true);
            edges.add(lastEdge);
            edges.add(new Edge(lastEdge.getId() + 1));

            newId = lastIntersection.getId() + 1;
            Intersection intersection = new Intersection(newId, edges);
            intersectionList.add(intersection);
        } while (newId < 6);
        Edge lastEdge = intersectionList.get(intersectionList.size() - 1).getLastEdge();
        firstIntersection.getEdges().add(lastEdge);
        return new FirstPiece(intersectionList);
    }

    private Intersection getLastIntersection(List<Intersection> intersectionList) {
        int max = 0;
        Intersection lastIntersection = new Intersection();
        for (Intersection intersection : intersectionList) {
            if (intersection.getId() > max) {
                max = intersection.getId();
                lastIntersection = intersection;
            }
        }
        return lastIntersection;
    }

    private Edge getLastEdge(List<Intersection> intersectionList) {
        int max = 0;
        Edge lastEdge = new Edge();
        for (Intersection intersection : intersectionList) {
            for (Edge edge : intersection.getEdges()) {
                if (edge.getId() > max) {
                    max = edge.getId();
                    lastEdge = edge;
                }
            }
        }
        return lastEdge;
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

    public void distributingNumbers() {
        List<Integer> numbers = new ArrayList<>(List.of(2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12));
        Collections.shuffle(numbers);
        int i = 0;
        for (BoardPosition boardPosition : board.getPositions()) {
            if (!boardPosition.getElement().getName().equals(THIEF))
                boardPosition.setNumber(numbers.get(i));
            else
                numbers.add(numbers.get(i));
            i++;
        }
    }

}
