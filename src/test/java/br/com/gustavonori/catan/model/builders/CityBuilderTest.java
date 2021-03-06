package br.com.gustavonori.catan.model.builders;

import br.com.gustavonori.catan.model.board.Board;
import br.com.gustavonori.catan.model.board.BoardBuilder;
import br.com.gustavonori.catan.model.constructions.ConstructionsTest;
import br.com.gustavonori.catan.model.models.elements.Element;
import br.com.gustavonori.catan.model.models.player.Player;
import br.com.gustavonori.catan.model.services.PlayerService;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static br.com.gustavonori.catan.model.models.elements.Elements.ROCK;
import static br.com.gustavonori.catan.model.models.elements.Elements.WHEAT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CityBuilderTest extends ConstructionsTest {

    @Before
    public void setUp() {
        playerService = new PlayerService(new Player(1, "Gustavo"));
        List<Element> elementsPlayer = List.of(
                new Element(WHEAT,2),
                new Element(ROCK,3)
        );
        elementsPlayer.forEach((element) -> {
            playerService.receivingElements(element.getName(), element.getQuantity());
        });
        construction = new CityBuilder();
        elementToRemove = WHEAT;
        boardBuilder = new BoardBuilder(new Board());
        boardBuilder.start();
        boardBuilder.distributingNumbers();
    }

    @Test
    public void testCheckBuildFail() {
        playerService.removingElements(Map.of(elementToRemove, 1));
        construction.build(playerService);
        assertTrue(playerService.getPlayer().getConstructions().isEmpty());
        assertThat(playerService.getPlayer().getElements(), hasItems(
                allOf(
                        hasProperty("name", is(WHEAT)),
                        hasProperty("quantity", is(1))
                ),
                allOf(
                        hasProperty("name", is(ROCK)),
                        hasProperty("quantity", is(3))
                )));
    }

    @Test
    public void testCheckBuildSuccess() {
        construction.build(playerService);
        assertThat(playerService.getPlayer().getElements(), hasItems(
                allOf(
                        hasProperty("name", is(WHEAT)),
                        hasProperty("quantity", is(0))
                ),
                allOf(
                        hasProperty("name", is(ROCK)),
                        hasProperty("quantity", is(0))
                )));
        MatcherAssert.assertThat(playerService.getPlayer().getConstructions(), hasItems(
                allOf(
                        hasProperty("name", is("CITY")))));
    }

    @Test
    public void testCheckPositionSuccess(){
        String position = "9G";
        List<PlayerService> players = new ArrayList<>();
        playerService.getPlayer().setConstructions(List.of(new VillageBuilder()));
        playerService.getPlayer().getConstructions().get(0).setPosition(position);
        players.add(playerService);
        assertTrue(construction.checkPosition(boardBuilder, players, position));
    }

    @Test
    public void testCheckPositionFail(){
        String position = "13E";
        List<PlayerService> players = new ArrayList<>();
        players.add(playerService);
        assertTrue(construction.checkPosition(boardBuilder, players, position));
    }
}