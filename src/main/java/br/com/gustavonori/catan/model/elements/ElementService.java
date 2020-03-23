package br.com.gustavonori.catan.model.elements;

import br.com.gustavonori.catan.model.player.RemovingElementException;

public class ElementService {
    private Element element;

    public ElementService(Element element) {
        this.element = element;
    }

    public void addElement(int quantity) {
        element.setQuantity(element.getQuantity() + quantity);
    }

    public void removeElement(int quantity) throws RemovingElementException {
        element.setQuantity(Math.max(element.getQuantity() - quantity, 0));
    }
}
