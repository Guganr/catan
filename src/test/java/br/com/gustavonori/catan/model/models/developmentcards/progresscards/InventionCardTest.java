package br.com.gustavonori.catan.model.models.developmentcards.progresscards;

import br.com.gustavonori.catan.model.models.developmentcards.DevelopmentCard;
import br.com.gustavonori.catan.model.models.developmentcards.InventionCard;
import br.com.gustavonori.catan.model.models.elements.Element;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.models.player.Player;
import br.com.gustavonori.catan.model.services.PlayerService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static br.com.gustavonori.catan.model.models.elements.Elements.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.allOf;
import static org.junit.jupiter.api.Assertions.*;

public class InventionCardTest {

    private PlayerService playerService;
    private DevelopmentCard inventionCard;

    @Before
    public void setUp(){
        playerService = new PlayerService(new Player(1, "Gustavo"));
        List<Element> elementsPlayer = List.of(
                new Element(WHEAT,2),
                new Element(ROCK,3)
        );
        elementsPlayer.forEach((element) -> {
            playerService.receivingElements(element.getName(), element.getQuantity());
        });
        InventionCard inventionCardCall = new InventionCard();
        inventionCard = inventionCardCall.getADevelopmentCardForUnitTest(false);
        playerService.getPlayer().getDevelopmentCards().add(inventionCard);
    }

    @Test
    public void specificAction() {
        Map<Elements, Integer> elementsPlayer = Map.of(
                ROCK,1,
                SHEEP,1
        );
        inventionCard.specificAction(playerService, elementsPlayer);
        assertThat(playerService.getPlayer().getElements(),
                hasItems(
                        allOf(
                                hasProperty("name", is(ROCK)),
                                hasProperty("quantity", is(4))
                        ),
                        allOf(
                                hasProperty("name", is(SHEEP)),
                                hasProperty("quantity", is(1))
                        )
                )
        );
    }

    @Test
    public void getADevelopmentCardForUnitTest() {
        DevelopmentCard aDevelopmentCardForUnitTest = inventionCard.getADevelopmentCardForUnitTest(false);
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
            if (card.equals(new InventionCard()) )
                check.set(true);
        });
        assertFalse(check.get());
    }
}