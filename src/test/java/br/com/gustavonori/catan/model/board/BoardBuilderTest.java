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
    final String test = "(Piece0(E1 - I1 I2 ) (E2 - I2 I3 ) (E3 - I3 I4 ) (E4 - I4 I5 ) (E5 - I5 I6 ) (E6 - I6 I1 ) ) (Piece1(E1 - I1 I2 ) (E7 - I1 I7 ) (E8 - I7 I8 ) (E9 - I8 I9 ) (E10 - I9 I10 ) (E11 - I10 I2 ) ) (Piece2(E2 - I2 I3 ) (E11 - I10 I2 ) (E12 - I10 I11 ) (E13 - I11 I12 ) (E14 - I12 I13 ) (E15 - I13 I3 ) ) (Piece3(E3 - I3 I4 ) (E15 - I13 I3 ) (E16 - I13 I14 ) (E17 - I14 I15 ) (E18 - I15 I16 ) (E19 - I16 I4 ) ) (Piece4(E4 - I4 I5 ) (E19 - I16 I4 ) (E20 - I16 I17 ) (E21 - I17 I18 ) (E22 - I18 I19 ) (E23 - I19 I5 ) ) (Piece5(E5 - I5 I6 ) (E23 - I19 I5 ) (E24 - I19 I20 ) (E25 - I20 I21 ) (E26 - I21 I22 ) (E27 - I22 I6 ) ) (Piece6(E6 - I6 I1 ) (E27 - I22 I6 ) (E28 - I22 I23 ) (E29 - I23 I24 ) (E30 - I24 I7 ) (E7 - I1 I7 ) ) (Piece7(E8 - I7 I8 ) (E30 - I24 I7 ) (E31 - I24 I25 ) (E32 - I25 I26 ) (E33 - I26 I27 ) (E34 - I27 I8 ) ) (Piece8(E9 - I8 I9 ) (E34 - I27 I8 ) (E35 - I27 I28 ) (E36 - I28 I29 ) (E37 - I29 I30 ) (E38 - I30 I9 ) ) (Piece9(E10 - I9 I10 ) (E38 - I30 I9 ) (E39 - I30 I31 ) (E40 - I31 I32 ) (E41 - I32 I11 ) (E12 - I10 I11 ) ) (Piece10(E13 - I11 I12 ) (E41 - I32 I11 ) (E42 - I32 I33 ) (E43 - I33 I34 ) (E44 - I34 I35 ) (E45 - I35 I12 ) ) (Piece11(E14 - I12 I13 ) (E45 - I35 I12 ) (E46 - I35 I36 ) (E47 - I36 I37 ) (E48 - I37 I14 ) (E16 - I13 I14 ) ) (Piece12(E17 - I14 I15 ) (E48 - I37 I14 ) (E49 - I37 I38 ) (E50 - I38 I39 ) (E51 - I39 I40 ) (E52 - I40 I15 ) ) (Piece13(E18 - I15 I16 ) (E52 - I40 I15 ) (E53 - I40 I41 ) (E54 - I41 I42 ) (E55 - I42 I17 ) (E20 - I16 I17 ) ) (Piece14(E21 - I17 I18 ) (E55 - I42 I17 ) (E56 - I42 I43 ) (E57 - I43 I44 ) (E58 - I44 I45 ) (E59 - I45 I18 ) ) (Piece15(E22 - I18 I19 ) (E59 - I45 I18 ) (E60 - I45 I46 ) (E61 - I46 I47 ) (E62 - I47 I20 ) (E24 - I19 I20 ) ) (Piece16(E25 - I20 I21 ) (E62 - I47 I20 ) (E63 - I47 I48 ) (E64 - I48 I49 ) (E65 - I49 I50 ) (E66 - I50 I21 ) ) (Piece17(E26 - I21 I22 ) (E66 - I50 I21 ) (E67 - I50 I51 ) (E68 - I51 I52 ) (E69 - I52 I23 ) (E28 - I22 I23 ) ) (Piece18(E29 - I23 I24 ) (E69 - I52 I23 ) (E70 - I52 I53 ) (E71 - I53 I54 ) (E72 - I54 I25 ) (E31 - I24 I25 ) )";
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
        assertEquals(test.trim(), boardBuilder.getBoard().toString().trim());
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