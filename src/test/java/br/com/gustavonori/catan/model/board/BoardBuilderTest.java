package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.board.positions.Edge;
import br.com.gustavonori.catan.model.board.positions.Intersection;
import br.com.gustavonori.catan.model.constructions.ConstructionsTest;
import br.com.gustavonori.catan.model.models.elements.Element;
import br.com.gustavonori.catan.model.models.elements.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;

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
    public void start(){
        boardBuilder.start();
        System.out.println(boardBuilder);
    }

    @Test
    public void pieceGetNext(){
        Piece firstPiece = boardBuilder.createFirstPiece();
        List<Edge> edges = new ArrayList<>();
        Edge firstEdge = firstPiece.getEdges().get(0);
        edges.add(firstEdge);
        edges.add(new Edge(7, List.of(firstEdge.getIntersections().get(1), new Intersection(7))));
        firstPiece.getIntersectionPieces().add(new Piece(edges));


        List<Edge> edges2 = new ArrayList<>();
        Edge secondEdge = firstPiece.getEdges().get(1);
        edges2.add(secondEdge);
        edges2.add(new Edge(8, List.of(secondEdge.getIntersections().get(1), new Intersection(7))));
        firstPiece.getIntersectionPieces().add(new Piece(edges2));
        Optional<Edge> nextOptional = firstPiece.getNext();
        Edge next = new Edge();
        if (nextOptional.isPresent())
            next = nextOptional.get();
        Assert.assertEquals(3, next.getId());
    }
}