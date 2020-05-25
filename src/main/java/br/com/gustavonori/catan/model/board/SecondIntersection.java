package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.board.parts.Piece;
import br.com.gustavonori.catan.model.board.positions.Intersection;

import java.util.ArrayList;
import java.util.List;

public class SecondIntersection extends PieceState {

    public SecondIntersection(Piece piece) {
        super(piece);
    }

    @Override
    public boolean isComplete() {
        if (piece.getIntersectionPieces().size() == 1)
            return false;
        List<Piece> intersectionPieces = piece.getIntersectionPieces();
        List<Intersection> intersectionList = intersectionPieces.get(0).getIntersection();
        List<Intersection> intersections = new ArrayList<>();
        intersections.add(piece.getIntersection().get(2));
        intersections.add(piece.getIntersection().get(3));
        return intersectionList.containsAll(intersections);
    }

    @Override
    public PieceState getNext() {
        return new ThirdIntersection(piece);
    }

    @Override
    public int getInitIntersectionPiece(int init) {
        switch (init) {
            case 3:
                return 1;
            case 4:
                return 0;
        }
        throw new RuntimeException("Não foi possível pegar a posição de início");
    }

    @Override
    public int getInitIntersectionPieceComplement(int init) {
        PieceState complement = new ThirdIntersection(piece);
        return complement.getInitIntersectionPiece(init);
    }

    @Override
    public int getInit() {
        return 3;
    }
}
