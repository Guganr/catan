package br.com.gustavonori.catan.model.services;

import br.com.gustavonori.catan.model.models.elements.Element;
import br.com.gustavonori.catan.model.models.player.RemovingElementException;
import org.springframework.stereotype.Service;


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
