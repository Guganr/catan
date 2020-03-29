package br.com.gustavonori.catan.model.constructions;

import br.com.gustavonori.catan.model.constructions.builders.Constructions;
import br.com.gustavonori.catan.model.constructions.builders.RoadBuilder;
import br.com.gustavonori.catan.model.elements.Element;
import br.com.gustavonori.catan.model.elements.Elements;
import br.com.gustavonori.catan.model.player.Player;
import br.com.gustavonori.catan.model.player.PlayerService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static br.com.gustavonori.catan.model.elements.Elements.WOOD;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class ConstructionsTest {

    protected PlayerService playerService;
    protected Constructions construction;
    protected Elements elementToRemove;

    @Test
    public void testCheckElementsSuccess() {
        assertTrue(construction.checkElements(playerService.getPlayer().getElements()));
    }

    @Test
    public void testCheckElementsFail() {
        playerService.removingElements(Map.of(elementToRemove, 1));
        assertFalse(construction.checkElements(playerService.getPlayer().getElements()));
    }

}