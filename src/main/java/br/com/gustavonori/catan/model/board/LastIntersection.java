package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.board.parts.Piece;
import br.com.gustavonori.catan.model.board.positions.Intersection;

import java.util.ArrayList;
import java.util.List;

public class LastIntersection extends PieceState {

    public LastIntersection(Piece piece) {
        super(piece);
    }

    @Override
    public boolean isComplete() {
        List<Piece> intersectionPieces = piece.getIntersectionPieces();
        List<Intersection> intersections = new ArrayList<>();
        intersections.add(piece.getIntersection().get(5));
        intersections.add(piece.getIntersection().get(6));
        return intersectionPieces.containsAll(intersections);
    }

    @Override
    public PieceState getNext() {
        throw new RuntimeException("Orçamento já finalizado");
    }

    @Override
    public int getInitIntersectionPiece(int init) {
        switch (init) {
            case 0:
                return 2;
            case 5:
                return 3;
        }
        throw new RuntimeException("Não foi possível pegar a posição de início");
    }

    @Override
    public int getInitIntersectionPieceComplement(int init) {
        throw new RuntimeException("Essa intersecção não possui complemento");
    }

    @Override
    public int getInit() {
        return 5;
    }
}
