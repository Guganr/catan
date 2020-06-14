package br.com.gustavonori.catan.model.models.player;

import br.com.gustavonori.catan.model.board.BoardBuilder;
import br.com.gustavonori.catan.model.board.positions.Edge;
import br.com.gustavonori.catan.model.board.positions.Intersection;
import br.com.gustavonori.catan.model.builders.RoadBuilder;
import br.com.gustavonori.catan.model.builders.VillageBuilder;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.services.PlayerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static br.com.gustavonori.catan.model.models.elements.Elements.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PlayerServiceTest extends ElementsForCatanTest {
    private Player player;
    private PlayerService playerService;

    @Before
    public void setUp() {
        player = new Player(1, "Gustavo");
        playerService = new PlayerService(player);
        setUpBoard();
    }

    @Test
    public void receivingElements() {
        Map<Elements, Integer> elements = new HashMap<>();
        elements.put(BRICK, 2);
        elements.put(WOOD, 1);
        addElements(elements);
        assertThat(player.getElements(), hasItems(
                allOf(
                        hasProperty("name", is(WOOD)),
                        hasProperty("quantity", is(1))
                ),
                allOf(
                        hasProperty("name", is(BRICK)),
                        hasProperty("quantity", is(2))
                )
        ));
    }

    @Test
    public void removingElements() {
        Map<Elements, Integer> elementsAdd = new HashMap<>();
        elementsAdd.put(SHEEP, 1);
        elementsAdd.put(WHEAT, 1);
        elementsAdd.put(ROCK, 5);
        addElements(elementsAdd);

        Map<Elements, Integer> elementsRemoved = new HashMap<>();
        elementsRemoved.put(SHEEP, 1);
        elementsRemoved.put(WHEAT, 2);
        elementsRemoved.put(ROCK, 3);
        playerService.removingElements(elementsRemoved);

        assertThat(player.getElements(), hasItems(
                allOf(
                        hasProperty("name", is(SHEEP)),
                        hasProperty("quantity", is(0))
                ),
                allOf(
                        hasProperty("name", is(WHEAT)),
                        hasProperty("quantity", is(0))
                ),
                allOf(
                        hasProperty("name", is(ROCK)),
                        hasProperty("quantity", is(2))
                )
        ));
    }

    @Test
    public void buildingRoad() {
        Map<Elements, Integer> elementsAdd = new HashMap<>();
        elementsAdd.put(BRICK, 3);
        elementsAdd.put(WOOD, 3);
        addElements(elementsAdd);
        Edge edge3 = board.getEdgeById(3);
        Edge edge15 = board.getEdgeById(15);
        Edge edge16 = board.getEdgeById(16);
        playerService.buildingRoad(edge3);
        playerService.buildingRoad(edge15);
        playerService.buildingRoad(edge16);

        Player otherPlayer = new Player(2, "Marjory");
        PlayerService otherPlayerService = new PlayerService(otherPlayer);
        Edge edge2 = board.getEdgeById(2);
        otherPlayerService.buildingRoad(edge2);

        assertThat(player.getElements(), hasItems(
                allOf(
                        hasProperty("name", is(BRICK)),
                        hasProperty("quantity", is(0))
                ),
                allOf(
                        hasProperty("name", is(WOOD)),
                        hasProperty("quantity", is(0))
                )
        ));

        Intersection intersection = board.getIntersectionById(3);
        Assert.assertTrue(board.isThereASquenceOfRoads(intersection, playerService));
    }

    @Test
    public void buildingRoadFailed2() {
        Map<Elements, Integer> elementsAdd = new HashMap<>();
        elementsAdd.put(BRICK, 2);
        elementsAdd.put(WOOD, 2);
        addElements(elementsAdd);
        Edge edge3 = board.getEdgeById(3);
        Edge edge15 = board.getEdgeById(15);
        playerService.buildingRoad(edge3);
        playerService.buildingRoad(edge15);

        Player otherPlayer = new Player(2, "Marjory");
        PlayerService otherPlayerService = new PlayerService(otherPlayer);
        Edge edge2 = board.getEdgeById(2);
        Edge edge16 = board.getEdgeById(16);
        otherPlayerService.buildingRoad(edge2);
        otherPlayerService.buildingRoad(edge16);

        Intersection intersection = board.getIntersectionById(3);
        Assert.assertFalse(board.isThereASquenceOfRoads(intersection, playerService));
    }

    @Test
    public void buildingRoadFailed() {
        Map<Elements, Integer> elementsAdd = new HashMap<>();
        elementsAdd.put(BRICK, 2);
        elementsAdd.put(WOOD, 2);
        addElements(elementsAdd);
        Edge edge3 = board.getEdgeById(3);
        playerService.buildingRoad(edge3);

        Player otherPlayer = new Player(2, "Marjory");
        PlayerService otherPlayerService = new PlayerService(otherPlayer);
        Edge edge2 = board.getEdgeById(2);
        otherPlayerService.buildingRoad(edge2);

        Intersection intersection = board.getIntersectionById(3);
        Assert.assertFalse(board.isThereASquenceOfRoads(intersection, playerService));
    }

    @Test
    public void buildRoad() {
        Map<Elements, Integer> elements = new HashMap<>();
        elements.put(BRICK, 2);
        elements.put(WOOD, 1);
        addElements(elements);
        playerService.buildingConstructions(new RoadBuilder());
        assertThat(player.getElements(), hasItems(
                allOf(
                        hasProperty("name", is(WOOD)),
                        hasProperty("quantity", is(0))
                ),
                allOf(
                        hasProperty("name", is(BRICK)),
                        hasProperty("quantity", is(1))
                )
        ));

        assertThat(player.getConstructions(), hasItem(
                allOf(
                        hasProperty("name", is("ROAD")),
                        hasProperty("points", is(0))
                )
        ));
    }

    @Test
    public void buildVillage() {
        Map<Elements, Integer> elements = new HashMap<>();
        elements.put(BRICK, 2);
        elements.put(WHEAT, 2);
        elements.put(SHEEP, 2);
        elements.put(WOOD, 1);
        addElements(elements);
        playerService.buildingConstructions(new VillageBuilder());
        assertThat(player.getElements(), hasItems(
                allOf(
                        hasProperty("name", is(WOOD)),
                        hasProperty("quantity", is(0))
                ),
                allOf(
                        hasProperty("name", is(BRICK)),
                        hasProperty("quantity", is(1))
                ),
                allOf(
                        hasProperty("name", is(WHEAT)),
                        hasProperty("quantity", is(1))
                ),
                allOf(
                        hasProperty("name", is(SHEEP)),
                        hasProperty("quantity", is(1))
                )
        ));

        assertThat(player.getConstructions(), hasItem(
                allOf(
                        hasProperty("name", is("VILLAGE")),
                        hasProperty("points", is(1))
                )
        ));
    }


    @Test
    public void buildCity() {
        Map<Elements, Integer> elements = new HashMap<>();
        elements.put(BRICK, 2);
        elements.put(WHEAT, 2);
        elements.put(SHEEP, 2);
        elements.put(WOOD, 1);
        addElements(elements);
        playerService.buildingConstructions(new VillageBuilder());
        assertThat(player.getElements(), hasItems(
                allOf(
                        hasProperty("name", is(WOOD)),
                        hasProperty("quantity", is(0))
                ),
                allOf(
                        hasProperty("name", is(BRICK)),
                        hasProperty("quantity", is(1))
                ),
                allOf(
                        hasProperty("name", is(WHEAT)),
                        hasProperty("quantity", is(1))
                ),
                allOf(
                        hasProperty("name", is(SHEEP)),
                        hasProperty("quantity", is(1))
                )
        ));

        assertThat(player.getConstructions(), hasItem(
                allOf(
                        hasProperty("name", is("VILLAGE")),
                        hasProperty("points", is(1))
                )
        ));
    }

    public void addElements(Map<Elements, Integer> elements) {
        elements.forEach((name, quantity) -> {
            playerService.receivingElements(name, quantity);
        });
    }
}