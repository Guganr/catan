package br.com.gustavonori.catan.model.elements;

import org.junit.Assert;
import org.junit.Test;

public class ElementServiceTest {
    ElementService elementService;

    @Test
    public void getWood(){
        Element wood = new Element(Elements.WOOD, 1);
        elementService = new ElementService(wood);
        elementService.removeElement(2);
        Assert.assertEquals(Elements.WOOD, wood.getName());
        Assert.assertEquals(0, wood.getQuantity());
    }

    @Test
    public void getSheep(){
        Element sheep = new Element(Elements.SHEEP, 2);
        elementService = new ElementService(sheep);
        elementService.removeElement(1);
        Assert.assertEquals(Elements.SHEEP, sheep.getName());
        Assert.assertEquals(1, sheep.getQuantity());
    }

    @Test
    public void getRock(){
        Element rock = new Element(Elements.ROCK, 3);
        elementService = new ElementService(rock);
        elementService.addElement(2);
        Assert.assertEquals(Elements.ROCK, rock.getName());
        Assert.assertEquals(5, rock.getQuantity());
    }

    @Test
    public void getWheat(){
        Element wheat = new Element(Elements.WHEAT, 2);
        elementService = new ElementService(wheat);
        elementService.removeElement(2);
        Assert.assertEquals(Elements.WHEAT, wheat.getName());
        Assert.assertEquals(0, wheat.getQuantity());
    }
}