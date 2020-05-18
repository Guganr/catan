package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.board.parts.Piece;

public abstract class PieceState {
    Piece piece;

    PieceState(Piece piece) {
        this.piece = piece;
    }

    public abstract String onFirstIntersection();
    public abstract String onMiddleIntersection();
    public abstract String onLastIntersection();
}