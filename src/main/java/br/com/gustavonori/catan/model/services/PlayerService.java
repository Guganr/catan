package br.com.gustavonori.catan.model.services;

import br.com.gustavonori.catan.model.board.BoardBuilder;
import br.com.gustavonori.catan.model.builders.Constructions;
import br.com.gustavonori.catan.model.models.elements.Element;
import br.com.gustavonori.catan.model.models.player.RemovingElementException;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.models.player.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

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

    public Element prepareReceivingElements(Elements elements) {
        return this.getPlayer().getElements().get(this.getElementIndex(elements));
    }

    public void receivingElements(Elements elements, int quantity) {
        ElementService elementService = new ElementService(prepareReceivingElements(elements));
        elementService.addElement(quantity);
    }

    private boolean checkElements(List<Element> elements, Constructions construction) {
        Map<Elements, Integer> elementsToBuild = construction.getElementsToBuild();
        AtomicBoolean check = new AtomicBoolean(true);
        for (Element element : elements) {
            elementsToBuild.forEach((el, qty) -> {
                if (element.getName().equals(el)) {
                    if (element.getQuantity() < qty){
                        check.set(false);
                    }
                }
            });
        }
        return check.get();
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

    private int rollTheDice() {
        Random r = new Random();
        return r.nextInt((12 - 1) + 1) + 1;
    }
}
