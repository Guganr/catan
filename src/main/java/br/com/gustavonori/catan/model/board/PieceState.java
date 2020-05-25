package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.board.parts.Piece;

public abstract class PieceState {
    Piece piece;

    PieceState(Piece piece) {
        this.piece = piece;
    }

    public abstract boolean isComplete();
    public abstract PieceState getNext();
    public abstract int getInitIntersectionPiece(int init);
    public abstract int getInitIntersectionPieceComplement(int init);
    public abstract int getInit();
}