package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.board.parts.Border;
import br.com.gustavonori.catan.model.board.parts.Internal;
import br.com.gustavonori.catan.model.board.parts.Piece;
import br.com.gustavonori.catan.model.board.positions.Edge;
import br.com.gustavonori.catan.model.board.positions.Intersection;
import br.com.gustavonori.catan.model.models.elements.Element;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static br.com.gustavonori.catan.model.models.elements.Elements.*;
import static br.com.gustavonori.catan.model.models.elements.Elements.WHEAT;
import static org.junit.jupiter.api.Assertions.*;

public class BoardBuilderTest {
    BoardBuilder boardBuilder;

    @Before
    public void setUp() {
        boardBuilder = new BoardBuilder(new Board());
    }

    @Test
    public void testPopulateElements(){
        List<Element> elements = boardBuilder.populateElements();
        int wood = (int) elements.stream().filter(element -> element.getName().equals(WOOD)).count();;
        int rock = (int) elements.stream().filter(element -> element.getName().equals(ROCK)).count();
        int sheep = (int) elements.stream().filter(element -> element.getName().equals(SHEEP)).count();
        int wheat = (int) elements.stream().filter(element -> element.getName().equals(WHEAT)).count();
        int brick = (int) elements.stream().filter(element -> element.getName().equals(BRICK)).count();
        assertEquals(4, wheat);
        assertEquals(4, wood);
        assertEquals(4, sheep);
        assertEquals(3, rock);
        assertEquals(3, brick);
    }

    @Test
    public void goku() {
        boardBuilder.putYourHandsUp();
        System.out.println(boardBuilder.getBoard());
    }

    @Test
    public void face() {
        Piece firstPositions = boardBuilder.createFirstPositions();
        boardBuilder.getBoard().getPositions().add(new BoardPosition(firstPositions));
        boardBuilder.buildForTest(firstPositions);
        boardBuilder.getBoard().getPositions();
    }

    @Test
    public void getIntersectionsToBuild2() {
        Piece firstPositions = boardBuilder.createFirstPositions();
        Piece secondPosition = new Border(createSecondPosition(firstPositions));
        firstPositions.getIntersectionPieces().add(secondPosition);
        Piece thirdPosition = new Internal(createThirdPosition(firstPositions, secondPosition));
        firstPositions.getIntersectionPieces().add(thirdPosition);
        boardBuilder.getIntersectionsToBuild2(firstPositions);
        boardBuilder.getBoard().getPositions();
    }

    private List<Intersection> createSecondPosition(Piece firstPositions) {
        Edge edge7 = new Edge(7);
        Edge edge8 = new Edge(8);
        Edge edge9 = new Edge(9);
        Edge edge10 = new Edge(10);
        Edge edge11 = new Edge(11);
        Intersection intersection7 = new Intersection(7);
        Intersection intersection8 = new Intersection(8);
        Intersection intersection9 = new Intersection(9);
        Intersection intersection10 = new Intersection(10);
        List<Intersection> intersectionList = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        edges.add(edge7);
        firstPositions.getIntersection().get(2).getEdges().addAll(edges);
        intersectionList.add(firstPositions.getIntersection().get(2));
        edges.add(edge8);
        intersection7.getEdges().addAll(edges);
        edges.clear();
        edges.add(edge8);
        edges.add(edge9);
        intersection8.getEdges().addAll(edges);
        edges.clear();
        edges.add(edge9);
        edges.add(edge10);
        intersection9.getEdges().addAll(edges);
        edges.clear();
        edges.add(edge10);
        edges.add(edge11);
        intersection10.getEdges().addAll(edges);
        intersectionList.add(intersection7);
        intersectionList.add(intersection8);
        intersectionList.add(intersection9);
        intersectionList.add(intersection10);
        firstPositions.getIntersection().get(3).getEdges().add(edge11);
        intersectionList.add(firstPositions.getIntersection().get(3));
        return intersectionList;
    }

    private List<Intersection> createThirdPosition(Piece firstPositions, Piece secondPosition) {
        Edge edge12 = new Edge(12);
        Edge edge13 = new Edge(13);
        Edge edge14 = new Edge(14);
        Edge edge15 = new Edge(15);
        Intersection intersection11 = new Intersection(11);
        Intersection intersection12 = new Intersection(12);
        Intersection intersection13 = new Intersection(13);
        List<Intersection> intersectionList = new ArrayList<>();
        intersectionList.add(firstPositions.getIntersection().get(4));
        intersectionList.add(firstPositions.getIntersection().get(3));
        secondPosition.getIntersection().get(4).getEdges().add(edge12);
        intersectionList.add(secondPosition.getIntersection().get(4));
        List<Edge> edges = new ArrayList<>();
        edges.add(edge12);
        edges.add(edge13);
        intersection11.getEdges().addAll(edges);
        intersectionList.add(intersection11);
        edges.clear();
        edges.add(edge13);
        edges.add(edge14);
        intersection12.getEdges().addAll(edges);
        intersectionList.add(intersection12);
        edges.clear();
        edges.add(edge14);
        edges.add(edge15);
        firstPositions.getIntersection().get(4).getEdges().add(edge15);
        intersection13.getEdges().addAll(edges);
        intersectionList.add(intersection13);
        edges.clear();
        return intersectionList;
    }
}