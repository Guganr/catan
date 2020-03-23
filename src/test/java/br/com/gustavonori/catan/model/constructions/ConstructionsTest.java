package br.com.gustavonori.catan.model.constructions;

import br.com.gustavonori.catan.model.elements.Element;
import br.com.gustavonori.catan.model.elements.Elements;
import br.com.gustavonori.catan.model.player.Player;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ConstructionsTest {

    private Player player;
    private RoadBuilder roadBuilder;

    @Before
    public void setUp(){
        player = new Player(1, "Gustavo");
        List<Element> elementsPlayer = List.of(
                new Element(Elements.WOOD,1),
                new Element(Elements.BRICK,1)
        );
        player.setElements(elementsPlayer);
        roadBuilder = new RoadBuilder();
    }
//
//    @Test
//    public void buildSuccess() {
//        assertTrue(roadBuilder.build(player));
//    }
//
//    @Test
//    public void buildFailed() {
//        assertTrue(roadBuilder.build(player));
//    }
}