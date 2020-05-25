package br.com.gustavonori.catan.model.board.parts;

import br.com.gustavonori.catan.model.board.positions.Intersection;

import java.util.List;

public class Internal extends Piece {
    public Internal(List<Intersection> intersection) {
        super(intersection);
    }

    public Internal() {
        super();
    }

    @Override
    public int getInitial(Piece lastPiece) {
        return 2;
    }

    @Override
    public int getInitial() {
        if (intersectionPieces.size() == 0)
            return 2;
        if (intersectionPieces.size() == 1)
            return 3;
        if (intersectionPieces.size() == 2)
            return 4;
        else
            return 2;
    }
    @Override
    public Piece createNewPieceFromLastOne() {
        return null;
    }

    @Override
    public Piece createNewPieceFromLastOne(Piece piece) {
        return null;
    }

    @Override
    public boolean hasNext() {
        return getIntersectionPieces().size() < 7;
    }
}