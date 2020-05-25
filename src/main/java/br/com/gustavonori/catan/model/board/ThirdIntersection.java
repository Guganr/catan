package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.board.parts.Piece;
import br.com.gustavonori.catan.model.board.positions.Intersection;

import java.util.ArrayList;
import java.util.List;

public class ThirdIntersection extends PieceState {
    public ThirdIntersection(Piece piece) {
        super(piece);
    }

    @Override
    public boolean isComplete() {
        if (piece.getIntersectionPieces().size() == 2)
            return false;
        List<Piece> intersectionPieces = piece.getIntersectionPieces();
        List<Intersection> intersectionList = intersectionPieces.get(2).getIntersection();
        List<Intersection> intersections = new ArrayList<>();
        intersections.add(piece.getIntersection().get(4));
        intersections.add(piece.getIntersection().get(5));
        return intersectionList.containsAll(intersections);
    }


    @Override
    public PieceState getNext() {
        return new LastIntersection(piece);
    }

    @Override
    public int getInitIntersectionPiece(int init) {
        switch (init) {
            case 4:
                return 2;
            case 5:
                return 1;
        }
        throw new RuntimeException("Não foi possível pegar a posição de início");
    }

    @Override
    public int getInitIntersectionPieceComplement(int init) {
        PieceState complement = new LastIntersection(piece);
        return complement.getInitIntersectionPiece(init);
    }

    @Override
    public int getInit() {
        return 4;
    }
}
