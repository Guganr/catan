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

        BoardBuilder boardBuilder = new BoardBuilder(new Board());
        boardBuilder.start();
        boardBuilder.distributingNumbers();
        System.out.println(boardBuilder);
    }
}
