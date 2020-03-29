package br.com.gustavonori.catan.model.models.elements;

import br.com.gustavonori.catan.model.models.elements.Elements;

public class Element {
    protected Elements name;
    protected int quantity;

    public Element(Elements name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    public Element(Elements name) {
        this.name = name;
        this.quantity = 0;
    }

    public Elements getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
