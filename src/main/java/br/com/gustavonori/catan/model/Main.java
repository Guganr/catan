package br.com.gustavonori.catan.model;

import br.com.gustavonori.catan.model.board.Board;
import br.com.gustavonori.catan.model.board.BoardBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        Board board = new Board();
        BoardBuilder bb = new BoardBuilder();
        bb.start(board);
        System.out.println(board);
    }
}
