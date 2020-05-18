package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.board.parts.Piece;
import br.com.gustavonori.catan.model.board.positions.Edge;
import br.com.gustavonori.catan.model.board.positions.Intersection;
import br.com.gustavonori.catan.model.board.positions.Positions;
import br.com.gustavonori.catan.model.models.elements.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardPosition {
//    private int position;
    private Element element;
    private Map<Integer, String> mapping;
    private int number;
    private List<Piece> pieces;

//    public BoardPosition(int position, Element element, Map<Integer, String> mapping, int number) {
//        this.position = position;
//        this.element = element;
//        this.mapping = mapping;
//        this.number = number;
//    }
//
//    public BoardPosition(int position, Element element, List<Intersection> positions, int number) {
//        this.position = position;
//        this.element = element;
//        this.positions = positions;
//        this.number = number;
//    }

    public BoardPosition(Piece piece) {
        if(this.pieces == null)
            this.pieces = new ArrayList<>();
        if(piece != null)
            this.pieces.add(piece);
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
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
