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

import static br.com.gustavonori.catan.model.models.elements.Elements.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        elementsPlayer.forEach((element) -> {
            playerService.receivingElements(element.getName(), element.getQuantity());
        });
        construction = new VillageBuilder();
        elementToRemove = WOOD;
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
        MatcherAssert.assertThat(playerService.getPlayer().getConstructions(), hasItems(
                allOf(
                        hasProperty("name", is("VILLAGE")))));
    }


    @Test
    public void testCheckPositionSuccess(){
        position = "9G";
        List<PlayerService> players = new ArrayList<>();
        players.add(playerService);
        players.add(new PlayerService(new Player(2, "Marjory")));
        construction.setPosition(position);
        assertTrue(construction.checkPosition(boardBuilder, players, position));
        assertEquals(position, construction.getPosition());
    }

    @Test
    public void testCheckPositionWithoutNumberImpair(){
        position = "10H";
        List<PlayerService> players = new ArrayList<>();
        players.add(playerService);
        players.add(new PlayerService(new Player(2, "Marjory")));
        assertFalse(construction.checkPosition(boardBuilder, players, position));
    }

    @Test
    public void testCheckPositionOutOfRange(){
        position = "1A";
        List<PlayerService> players = new ArrayList<>();
        players.add(playerService);
        players.add(new PlayerService(new Player(2, "Marjory")));
        assertFalse(construction.checkPosition(boardBuilder, players, position));
    }

    @Test
    public void testCheckPositionInUse(){
        String position = "9G";
        List<PlayerService> players = new ArrayList<>();
        players.add(playerService);
        PlayerService marjory = new PlayerService(new Player(2, "Marjory"));
        marjory.getPlayer().setConstructions(List.of(new RoadBuilder()));
        marjory.getPlayer().getConstructions().get(0).setPosition(position);
        players.add(marjory);
        assertFalse(construction.checkPosition(boardBuilder, players, position));
    }
}