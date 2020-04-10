package br.com.gustavonori.catan.model.models.developmentcards.progresscards;

import br.com.gustavonori.catan.model.models.developmentcards.DevelopmentCard;
import br.com.gustavonori.catan.model.models.developmentcards.InventionCard;
import br.com.gustavonori.catan.model.models.developmentcards.MonopolyCard;
import br.com.gustavonori.catan.model.models.elements.Element;
import br.com.gustavonori.catan.model.models.player.Player;
import br.com.gustavonori.catan.model.services.PlayerService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static br.com.gustavonori.catan.model.models.elements.Elements.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonopolyCardTest {

    private PlayerService playerService;
    private DevelopmentCard monopolyCard;

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
        MonopolyCard monopolyCardCall = new MonopolyCard();
        monopolyCard = monopolyCardCall.getADevelopmentCardForUnitTest(false);
        playerService.getPlayer().getDevelopmentCards().add(monopolyCard);
    }

    @Test
    public void specificAction() {
        
    }

    @Test
    public void getADevelopmentCardForUnitTest() {
        DevelopmentCard aDevelopmentCardForUnitTest = monopolyCard.getADevelopmentCardForUnitTest(false);
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