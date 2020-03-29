package br.com.gustavonori.catan.model.constructions;

import br.com.gustavonori.catan.model.builders.Constructions;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.services.PlayerService;
import org.junit.Test;

import java.util.Map;

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