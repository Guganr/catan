package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.board.parts.Piece;

public interface PieceBuilderState {

    void getNext(Piece piece);
}
