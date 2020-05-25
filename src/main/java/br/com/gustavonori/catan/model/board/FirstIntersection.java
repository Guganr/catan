package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.board.parts.Piece;
import br.com.gustavonori.catan.model.board.positions.Intersection;
import org.aspectj.weaver.Lint;

import java.util.ArrayList;
import java.util.List;

public class FirstIntersection extends PieceState {

    public FirstIntersection(Piece piece) {
        super(piece);
    }

    @Override
    public boolean isComplete() {
        if (piece.getIntersectionPieces().size() == 0)
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
        return new SecondIntersection(piece);
    }

    @Override
    public int getInitIntersectionPiece(int init) {
        switch (init) {
            case 2:
                return 0;
            case 3:
                return 5;
        }
        throw new RuntimeException("Não foi possível pegar a posição de início");
    }

    @Override
    public int getInitIntersectionPieceComplement(int init) {
        throw new RuntimeException("Essa intersecção não possui complemento");
    }

    @Override
    public int getInit() {
        return 2;
    }
}
