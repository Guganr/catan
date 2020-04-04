package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.models.elements.Element;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.thief.Thief;

import java.util.*;

import static br.com.gustavonori.catan.model.models.elements.Elements.*;

public class BoardBuilder {
    private Map<Integer, String> alphabet;
    private Map<Integer, String> initPositionsOnTheBoard;
    private List<Element> elementsList;

    public void start(Board board) {
        populateElements();
        populateAlphabet();
        initPositionsOnTheBoard();
        for (int initPosition = 0; initPosition < 18; initPosition++) {
            Element element = elementsList.get(initPosition);
            int number = 0;
            if (initPosition == 12) {
                element = new Thief(THIEF);
                number = 7;
            }
            board.getPositions().add(
                    new BoardPosition(initPosition, element,
                            calculatePositions(initPositionsOnTheBoard.get(initPosition)), number)
            );
        }
    }

    private void populateElements() {
        elementsList = new ArrayList<>();
        Map<Elements, Integer> elementsMap = new HashMap<>();
        elementsMap.put(WHEAT, 4);
        elementsMap.put(WOOD, 4);
        elementsMap.put(SHEEP, 4);
        elementsMap.put(ROCK, 3);
        elementsMap.put(BRICK, 3);
        elementsMap.forEach((element, qty) -> {
            for (int i = 0; i < qty; i++) {
                elementsList.add(new Element(element));
            }
        });

        Collections.shuffle(elementsList);
    }

    private Map<Integer, String> calculatePositions(String init) {
        Map<Integer, String> board = new HashMap<>();
        int length = init.length() - 1;
        String letter = String.valueOf(init.charAt(length));
        int key = Integer.parseInt(init.substring(0, length));

        for (int i = 0; i < 12; i++) {
            String val = key + letter;
            board.put(i, val);
            if (i < 6)
                key++;
            else
                key--;
            letter = getNextLetter(letter, i);
        }
        return board;
    }

    private Integer getAlphabetByValue(String letra) {
        return alphabet.entrySet().stream().filter(a -> a.getValue().equals(letra)).findFirst().get().getKey();
    }

    private String getNextLetter(String letra, int i) {
        int letraKey = getAlphabetByValue(letra);
        if (i < 2 || i == 10)
            letraKey--;
        else if (i > 3 && i < 8)
            letraKey++;
        return alphabet.get(letraKey);
    }

    private void populateAlphabet() {
        alphabet = new HashMap<>();
        alphabet.put(1, "A");
        alphabet.put(2, "B");
        alphabet.put(3, "C");
        alphabet.put(4, "D");
        alphabet.put(5, "E");
        alphabet.put(6, "F");
        alphabet.put(7, "G");
        alphabet.put(8, "H");
        alphabet.put(9, "I");
        alphabet.put(10, "J");
        alphabet.put(11, "K");
        alphabet.put(12, "L");
        alphabet.put(13, "M");
        alphabet.put(14, "N");
        alphabet.put(15, "O");
        alphabet.put(16, "P");
        alphabet.put(17, "Q");
        alphabet.put(18, "R");
        alphabet.put(19, "S");
        alphabet.put(20, "T");
        alphabet.put(21, "U");
    }

    private void initPositionsOnTheBoard() {
        initPositionsOnTheBoard = new HashMap<>();
        //PRIMEIRA FILEIRA
        initPositionsOnTheBoard.put(0, "1G");
        initPositionsOnTheBoard.put(1, "5E");
        initPositionsOnTheBoard.put(2, "9C");
        initPositionsOnTheBoard.put(3, "13E");
        initPositionsOnTheBoard.put(4, "17G");
        //SEGUNDA FILEIRA
        initPositionsOnTheBoard.put(5, "1K");
        initPositionsOnTheBoard.put(6, "5I");
        initPositionsOnTheBoard.put(7, "9G");
        initPositionsOnTheBoard.put(8, "13I");
        initPositionsOnTheBoard.put(9, "17K");
        //TERCEIRA FILEIRA
        initPositionsOnTheBoard.put(10, "1O");
        initPositionsOnTheBoard.put(11, "5M");
        initPositionsOnTheBoard.put(12, "9K");
        initPositionsOnTheBoard.put(13, "13M");
        initPositionsOnTheBoard.put(14, "17O");
        //TERCEIRA FILEIRA
        initPositionsOnTheBoard.put(15, "5Q");
        initPositionsOnTheBoard.put(16, "9O");
        initPositionsOnTheBoard.put(17, "13Q");
        //TERCEIRA FILEIRA
        initPositionsOnTheBoard.put(18, "9S");
    }
}
