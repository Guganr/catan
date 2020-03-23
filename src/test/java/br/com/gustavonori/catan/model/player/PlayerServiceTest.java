package br.com.gustavonori.catan.model.player;

import br.com.gustavonori.catan.model.constructions.builders.RoadBuilder;
import br.com.gustavonori.catan.model.constructions.builders.VillageBuilder;
import br.com.gustavonori.catan.model.elements.Elements;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static br.com.gustavonori.catan.model.elements.Elements.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PlayerServiceTest {
    private Player player;
    private PlayerService playerService;

    @Before
    public void setUp() {
        player = new Player(1, "Gustavo");
        playerService = new PlayerService(player);
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
        elementsAdd.put(BRICK, 1);
        elementsAdd.put(WOOD, 1);
        addElements(elementsAdd);
        playerService.removingElements(RoadBuilder.elementsToBuild);

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
            playerService.receivingElements(player.getElements().get(playerService.getElementIndex(name)), quantity);
        });
    }
}