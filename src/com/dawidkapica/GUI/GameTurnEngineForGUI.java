package com.dawidkapica.GUI;

import com.dawidkapica.engine.Connect4Fitness;
import com.dawidkapica.engine.Connect4Logic;
import com.dawidkapica.engine.Connect4MinMax;
import com.dawidkapica.information.Board;

public class GameTurnEngineForGUI {

    Board board = new Board();
    Connect4Logic connect4Logic = new Connect4Logic(board);
    Connect4Fitness connect4Fitness = new Connect4Fitness();
    boolean hasWinner = false;


    public boolean putDiscToEngine (int column, int color) {
        if (connect4Logic.addField(column, color) == -1) {
            return false;
        } else {
            System.out.println(board.toString());

            return true;
        }
    }

    public int aiTurn (int level, int color) {
        Connect4MinMax connect4MinMaxAi1 = new Connect4MinMax(level);

        int columnAI = connect4MinMaxAi1.choosePlace(board, color);
        int rowAI = connect4Logic.addField(columnAI, color);
        System.out.println(board.toString());

        hasWinner = connect4Logic.checkWin(rowAI, columnAI);
        if (hasWinner == true) {

            System.out.println("KONIEC");
        }

        return columnAI;
    }

    public boolean isHasWinner () {
        return hasWinner;
    }
}
