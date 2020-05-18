package br.com.gustavonori.catan.model.board.parts;

import br.com.gustavonori.catan.model.board.positions.Intersection;
import br.com.gustavonori.catan.model.models.elements.Element;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    protected int id;
    protected Element element;
    protected List<Intersection> intersection;
    protected int number;
    protected List<Piece> intersectionPieces;
    protected int position;

    public Piece(int id, Element element, List<Intersection> intersection, int number) {
        this.id = id;
        this.element = element;
        this.intersection = intersection;
        this.number = number;
        this.intersectionPieces = new ArrayList<>();
    }

    public Piece(List<Intersection> intersection) {
        this.intersection = intersection;
        this.intersectionPieces = new ArrayList<>();
    }

    public Piece() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public List<Intersection> getIntersection() {
        return intersection;
    }

    public void setIntersection(List<Intersection> intersection) {
        this.intersection = intersection;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Piece> getIntersectionPieces() {
        return intersectionPieces;
    }

    public void setIntersectionPieces(List<Piece> intersectionPieces) {
        this.intersectionPieces = intersectionPieces;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public abstract int getInitial(Piece lastPiece);

    public abstract int getInitial();

    public abstract Piece createNewPieceFromLastOne();

    public abstract Piece createNewPieceFromLastOne(Piece piece);

    public boolean hasNext() {
        return getIntersectionPieces().size() < 3;
    }
}
