package com.dawidkapica.information;

import java.util.ArrayList;

public class Board {

    ArrayList<ArrayList<Integer>> connect4Board = new ArrayList<ArrayList<Integer>>();

    ArrayList<Integer> connect4ColumnScore = new ArrayList<Integer>();

    private static final int EMPTY_FIELD = 0;
    private static final int RED_FIELD = -1;
    private static final int YELLOW_FIELD = 1;

    public Board() {
        for (int i = 0; i < 6; i ++) {
            connect4Board.add(new ArrayList<Integer>());
            for (int j = 0; j < 7; j++) {
                connect4Board.get(i).add(EMPTY_FIELD);
            }
        }
    }

    public Board(ArrayList<ArrayList<Integer>> connect4Board) {
        for (int i = 0; i < 6; i++) {
            this.connect4Board.add(i, new ArrayList<Integer>(connect4Board.get(i)));
        }
    }

    public Board(Board board) {
        for (int i = 0; i < 6; i++) {
            this.connect4Board.add(i, new ArrayList<Integer>(board.getConnect4Board().get(i)));
        }
    }

    public ArrayList<ArrayList<Integer>> getConnect4Board () {
        return connect4Board;
    }

    public void setConnect4Board (ArrayList<ArrayList<Integer>> connect4Board) {
        this.connect4Board = connect4Board;
    }

    public ArrayList<Integer> getConnect4ColumnScore () {
        return connect4ColumnScore;
    }

    public void setConnect4ColumnScore (ArrayList<Integer> connect4ColumnScore) {
        this.connect4ColumnScore = connect4ColumnScore;
    }

    public int getFieldValue(int indexH, int indexV) {
        return connect4Board.get(indexH).get(indexV);
    }

    public void setFieldValue(int indexH, int indexV, int value) {
        connect4Board.get(indexH).set(indexV, value);
    }

    public boolean putDisk(int column, int color) {
        int index = 0;
        for (int i = 5; i >= 0; i--) {
            if( connect4Board.get(i).get(column) == 0) {
                connect4Board.get(i).set(column, color);
                return true;
            }
        }
        return false;
    }

    public boolean checkIsFull() {
        for (int i = 0; i < connect4Board.size(); i++) {
            if(connect4Board.get(0).get(i) == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean checkIsFullColumn(int column) {
        if (connect4Board.get(0).get(column) != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString () {
        String boardString = new String("");

        for (int i = 0; i < 6; i++) {
            boardString = boardString + this.connect4Board.get(i).toString() + '\n';
        }
        return boardString;
    }
}
