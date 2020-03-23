package br.com.gustavonori.catan.model.player;

import br.com.gustavonori.catan.model.constructions.Constructions;
import br.com.gustavonori.catan.model.elements.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static br.com.gustavonori.catan.model.elements.Elements.*;

public class Player {
    private int id;
    private String name;
    private List<Element> elements;
    private List<Constructions> constructions;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.elements = (List.of(
                new Element(BRICK),
                new Element(ROCK),
                new Element(SHEEP),
                new Element(WHEAT),
                new Element(WOOD)));
        this.constructions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public List<Constructions> getConstructions() {
        return constructions;
    }

    public void setConstructions(List<Constructions> constructions) {
        this.constructions = constructions;
    }
}