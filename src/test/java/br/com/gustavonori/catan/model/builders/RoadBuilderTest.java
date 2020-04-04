package br.com.gustavonori.catan.model.builders;

import br.com.gustavonori.catan.model.constructions.ConstructionsTest;
import br.com.gustavonori.catan.model.models.elements.Element;
import br.com.gustavonori.catan.model.models.player.Player;
import br.com.gustavonori.catan.model.services.PlayerService;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static br.com.gustavonori.catan.model.models.elements.Elements.BRICK;
import static br.com.gustavonori.catan.model.models.elements.Elements.WOOD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoadBuilderTest extends ConstructionsTest {

    @Before
    public void setUp() {
        playerService = new PlayerService(new Player(1, "Gustavo"));
        List<Element> elementsPlayer = List.of(
                new Element(WOOD,1),
                new Element(BRICK,1)
        );
        playerService.getPlayer().getElements().addAll(elementsPlayer);
        construction = new RoadBuilder();
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
                        hasProperty("name", is(WOOD)),
                        hasProperty("quantity", is(0))
                )));
        MatcherAssert.assertThat(playerService.getPlayer().getConstructions(), hasItems(
                allOf(
                        hasProperty("name", is("ROAD")))));
    }
}