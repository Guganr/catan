package br.com.gustavonori.catan.model.models.player;

import br.com.gustavonori.catan.model.board.Board;
import br.com.gustavonori.catan.model.board.BoardBuilder;
import org.junit.Before;

public class ElementsForCatanTest {
    protected Board board;

    public void setUpBoard() {
        BoardBuilder boardBuilder = new BoardBuilder(new Board());
        boardBuilder.start();
        this.board = boardBuilder.getBoard();
    }

}
