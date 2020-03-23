package br.com.gustavonori.catan.model.player;

import br.com.gustavonori.catan.model.constructions.Constructions;
import br.com.gustavonori.catan.model.elements.Element;
import br.com.gustavonori.catan.model.elements.ElementService;
import br.com.gustavonori.catan.model.elements.Elements;

import java.util.List;
import java.util.Map;

public class PlayerService {
    private Player player;

    public PlayerService(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getElementIndex(Elements element) {
        List<Element> playerElements = this.player.getElements();
        Element elementMethod = playerElements.stream()
                .filter(element1 -> element1.getName().equals(element))
                .findFirst().get();
        return playerElements.indexOf(elementMethod);
    }

    public void receivingElements(Element element, int quantity) {
        ElementService elementService = new ElementService(element);
        elementService.addElement(quantity);
    }

    public void removingElements(Map<Elements, Integer> elements) throws RemovingElementException {
        elements.forEach((element, quantity) -> {
            player.getElements().stream()
                    .filter(e -> e.getName().equals(element))
                    .forEach(element1 -> {
                        ElementService elementService = new ElementService(element1);
                        elementService.removeElement(quantity);
                    });
        });
    }

    public void buildingConstructions(Constructions construction){
        construction.build(this);
    }
}