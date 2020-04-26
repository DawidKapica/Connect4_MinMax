package com.dawidkapica.information;

import java.util.ArrayList;

public class Node {

    private Board board;
    private ArrayList<Node> child = new ArrayList<Node>();

    private int turn = 0;

    private int value = 0;

    public Node(Board board, int turn) {
        this.board = board;
        this.turn = turn;

//        for (int i = 0; i < 7; i++) {
//            child.add(new Node(board));
//            if (turn == -1) {
//                child.get(i).setTurn(1);
//            } else {
//                child.get(i).setTurn(-1);
//            }
//        }

    }

    public Node(Board board) {
        this.board = new Board(board);
    }

    public Board getBoard () {
        return board;
    }

    public void setBoard (Board board) {
        this.board = board;
    }

    public ArrayList<Node> getChild () {
        return child;
    }

    public void setChild (ArrayList<Node> child) {
        this.child = child;
    }

    public int getTurn () {
        return turn;
    }

    public void setTurn (int turn) {
        this.turn = turn;
    }

    public int getValue () {
        return value;
    }

    public void setValue (int value) {
        this.value = value;
    }

    public void createChild() {
        for (int i = 0; i < 7; i++) {
            child.add(new Node(board));
            if (turn == -1) {
                child.get(i).setTurn(1);
            } else {
                child.get(i).setTurn(-1);
            }
        }
    }
}
