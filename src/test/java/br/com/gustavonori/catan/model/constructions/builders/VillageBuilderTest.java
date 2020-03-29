package br.com.gustavonori.catan.model.constructions.builders;

import br.com.gustavonori.catan.model.constructions.ConstructionsTest;
import br.com.gustavonori.catan.model.elements.Element;
import br.com.gustavonori.catan.model.elements.Elements;
import br.com.gustavonori.catan.model.player.Player;
import br.com.gustavonori.catan.model.player.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Map;

import static br.com.gustavonori.catan.model.elements.Elements.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class VillageBuilderTest extends ConstructionsTest {

    @Before
    public void setUp() {
        playerService = new PlayerService(new Player(1, "Gustavo"));
        List<Element> elementsPlayer = List.of(
                new Element(WOOD,1),
                new Element(SHEEP,1),
                new Element(WHEAT,1),
                new Element(BRICK,1)
        );
        playerService.getPlayer().setElements(elementsPlayer);
        construction = new VillageBuilder();
        elementToRemove = WOOD;
    }

    @Test
    public void testCheckBuildFail() {
        playerService.removingElements(Map.of(elementToRemove, 1));
        construction.build(playerService);
        assertTrue(playerService.getPlayer().getConstructions().isEmpty());
        assertThat(playerService.getPlayer().getElements(), hasItems(
                allOf(
                        hasProperty("name", is(BRICK)),
                        hasProperty("quantity", is(1))
                ),
                allOf(
                        hasProperty("name", is(SHEEP)),
                        hasProperty("quantity", is(1))
                ),
                allOf(
                        hasProperty("name", is(WHEAT)),
                        hasProperty("quantity", is(1))
                ),
                allOf(
                        hasProperty("name", is(WOOD)),
                        hasProperty("quantity", is(0))
                )));
    }

    @Test
    public void testCheckBuildSuccess() {
        construction.build(playerService);
        assertThat(playerService.getPlayer().getElements(), hasItems(
                allOf(
                        hasProperty("name", is(BRICK)),
                        hasProperty("quantity", is(0))
                ),
                allOf(
                        hasProperty("name", is(SHEEP)),
                        hasProperty("quantity", is(0))
                ),
                allOf(
                        hasProperty("name", is(WHEAT)),
                        hasProperty("quantity", is(0))
                ),
                allOf(
                        hasProperty("name", is(WOOD)),
                        hasProperty("quantity", is(0))
                )));
        assertThat(playerService.getPlayer().getConstructions(), hasItems(
                allOf(
                        hasProperty("name", is("VILLAGE")))));
    }
}