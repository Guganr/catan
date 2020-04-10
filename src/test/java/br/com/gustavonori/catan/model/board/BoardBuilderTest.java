package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.constructions.ConstructionsTest;
import br.com.gustavonori.catan.model.models.elements.Element;
import br.com.gustavonori.catan.model.models.elements.Elements;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static br.com.gustavonori.catan.model.models.elements.Elements.*;
import static br.com.gustavonori.catan.model.models.elements.Elements.WHEAT;
import static org.junit.jupiter.api.Assertions.*;

public class BoardBuilderTest {
    BoardBuilder boardBuilder;

    @Before
    public void setUp() {
        boardBuilder = new BoardBuilder();
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
    public void calculatePositions(){
        List<String> expectedMap = new ArrayList<>();
        expectedMap.addAll(List.of("1G","2F","3E","4E","5E","6F","7G","6H","5I","4I", "3I", "2H"));
        boardBuilder.populateAlphabet();
        Map<Integer, String> positions = boardBuilder.calculatePositions("1G");
        assertTrue(expectedMap.containsAll(positions.values()));
    }
}