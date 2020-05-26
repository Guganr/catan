package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.models.elements.Element;

import java.util.Map;

public class BoardPosition {
    private int position;
    private Element element;
    private Map<Integer, String> mapping;
    private Piece piece;
    private int number;

    public BoardPosition(int position, Element element, Map<Integer, String> mapping, int number) {
        this.position = position;
        this.element = element;
        this.mapping = mapping;
        this.number = number;
    }

    public BoardPosition(Piece piece) {
        this.piece = piece;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Map<Integer, String> getMapping() {
        return mapping;
    }

    public void setMapping(Map<Integer, String> mapping) {
        this.mapping = mapping;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
