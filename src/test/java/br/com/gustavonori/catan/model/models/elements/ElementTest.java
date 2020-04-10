package br.com.gustavonori.catan.model.models.elements;

import org.junit.Assert;
import org.junit.Test;

public class ElementTest {

    @Test
    public void getBrick(){
        Element brick = new Element(Elements.BRICK, 0);
        Assert.assertEquals(Elements.BRICK, brick.getName());
    }

    @Test
    public void getWood(){
        Element wood = new Element(Elements.WOOD, 1);
        Assert.assertEquals(Elements.WOOD, wood.getName());
        Assert.assertEquals(1, wood.getQuantity());
    }

    @Test
    public void getSheep(){
        Element sheep = new Element(Elements.SHEEP, 2);
        Assert.assertEquals(Elements.SHEEP, sheep.getName());
        Assert.assertEquals(2, sheep.getQuantity());
    }

    @Test
    public void getRock(){
        Element rock = new Element(Elements.ROCK, 3);
        Assert.assertEquals(Elements.ROCK, rock.getName());
        Assert.assertEquals(3, rock.getQuantity());
    }

    @Test
    public void getWheat(){
        Element wheat = new Element(Elements.WHEAT, 2);
        Assert.assertEquals(Elements.WHEAT, wheat.getName());
        Assert.assertEquals(2, wheat.getQuantity());
    }
}