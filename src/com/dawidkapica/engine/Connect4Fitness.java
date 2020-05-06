package com.dawidkapica.engine;

import com.dawidkapica.information.Board;

import java.util.ArrayList;
import java.util.List;

public class Connect4Fitness {

    public ArrayList<Integer> calculateFitnesses(Board board, int color) {
        ArrayList<Integer> scoreColumn = new ArrayList<>(List.of(0, 0, 0, 4, 0, 0, 0));

        for (int i = 0; i < scoreColumn.size(); i++) {
            Board boardAddValue = new Board(board);
            boardAddValue.putDisk(i, color);
            scoreColumn.set(i, calcScore(boardAddValue, color));
        }

        return scoreColumn;
    }

    public int calcScore(Board board, int color) {

        int vertical_points=0;
        int horizontal_points=0;
        int descDiagonal_points=0;
        int ascDiagonal_points=0;
        int total_points=0;

        for (int row = 0; row < 6 - 3; row++) {
            for (int column = 0; column < 7; column++) {
                int tempScore = calcScoreOnPosition(board, row, column, 1, 0, color);
//                if (tempScore == 10000) {
//                    return tempScore;
//                }
                vertical_points += tempScore;

            }
        }

        for (int row = 0; row < 6 ; row++) {
            for (int column = 0; column < 7 - 3; column++) {
                int tempScore = calcScoreOnPosition(board, row, column, 0, 1, color);
//                if (tempScore == 10000) {
//                    return tempScore;
//                }
                horizontal_points += tempScore;

            }
        }

        for (int row = 0; row < 6 - 3 ; row++) {
            for (int column = 0; column < 7 - 3; column++) {
                int tempScore = calcScoreOnPosition(board, row, column, 1, 1, color);
//                if (tempScore == 10000) {
//                    return tempScore;
//                }
                descDiagonal_points += tempScore;

            }
        }

        for (int row = 3; row < 6; row++) {
            for (int column = 0; column < 7 - 4; column++) {
                int tempScore = calcScoreOnPosition(board, row, column, -1, 1, color);
//                if (tempScore == 10000) {
//                    return tempScore;
//                }
                ascDiagonal_points += tempScore;

            }
        }

        total_points = vertical_points + horizontal_points + descDiagonal_points + ascDiagonal_points;
        return total_points;
    }

    public int calcScoreOnPosition(Board board, int row, int column, int increment_row, int increment_col, int color) {
        int playerMinus1Point = 0;
        int playerPlus1Point = 0;

        for (int i = 0; i < 4; i++)
        {
            if(board.getFieldValue(row, column) == -1)
            {
                playerMinus1Point++;
            }
            else if (board.getFieldValue(row, column) == 1)
            {
                playerPlus1Point++;
            }

            row += increment_row;
            column += increment_col;
        }

        if(playerMinus1Point == 4) {
            if (color == -1) {
                return 10000;
            } else {
                return -10000;
            }
        } else if(playerPlus1Point == 4)
            if (color == 1) {
                return 10000;
            } else {
                return -10000;
            }
        else {
            if (color == 1) {
                return playerPlus1Point;
            } else {
                return playerMinus1Point;
            }
        }
    }
}
