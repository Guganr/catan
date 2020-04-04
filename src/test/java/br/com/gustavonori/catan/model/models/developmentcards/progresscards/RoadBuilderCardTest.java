package br.com.gustavonori.catan.model.models.developmentcards.progresscards;

import br.com.gustavonori.catan.model.builders.CityBuilder;
import br.com.gustavonori.catan.model.constructions.ConstructionsTest;
import br.com.gustavonori.catan.model.models.developmentcards.DevelopmentCard;
import br.com.gustavonori.catan.model.models.elements.Element;
import br.com.gustavonori.catan.model.models.player.Player;
import br.com.gustavonori.catan.model.services.PlayerService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static br.com.gustavonori.catan.model.models.elements.Elements.*;
import static br.com.gustavonori.catan.model.models.elements.Elements.SHEEP;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class RoadBuilderCardTest {

    private PlayerService playerService;
    private DevelopmentCard roadBuilderCard;

    @Before
    public void setUp(){
        playerService = new PlayerService(new Player(1, "Gustavo"));
        List<Element> elementsPlayer = List.of(
                new Element(WHEAT,2),
                new Element(ROCK,3)
        );
        playerService.getPlayer().getElements().addAll(elementsPlayer);
        RoadBuilderCard roadBuilderCardCall = new RoadBuilderCard();
        roadBuilderCard = roadBuilderCardCall.getADevelopmentCardForUnitTest(false);
        playerService.getPlayer().getDevelopmentCards().add(roadBuilderCard);
    }

    @Test
    public void specificAction() {
        roadBuilderCard.specificAction(playerService, Collections.emptyMap());
        assertEquals(2, playerService.getPlayer().getConstructions().size());
        assertThat(playerService.getPlayer().getConstructions(),
                hasItem(
                        hasProperty("name", is("ROAD"))
                )
        );
    }

    @Test
    public void getADevelopmentCardForUnitTest() {
        DevelopmentCard aDevelopmentCardForUnitTest = roadBuilderCard.getADevelopmentCardForUnitTest(false);
        AtomicBoolean check = new AtomicBoolean(false);
        DevelopmentCard.getDevelopmentCardsMap().forEach((id, card) -> {
            if (card.equals(aDevelopmentCardForUnitTest) )
                check.set(true);
        });
        assertTrue(check.get());
    }

    @Test
    public void getADevelopmentCardForUnitTestFail() {
        AtomicBoolean check = new AtomicBoolean(false);
        DevelopmentCard.getDevelopmentCardsMap().forEach((id, card) -> {
            if (card.equals(new RoadBuilderCard()) )
                check.set(true);
        });
        assertFalse(check.get());
    }
}