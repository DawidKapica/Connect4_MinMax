package com.dawidkapica.information;

import com.dawidkapica.engine.Connect4Fitness;

import java.util.ArrayList;

public class Node {

    private Board board;
    private ArrayList<Node> child = new ArrayList<Node>();

    private int turn = 0;

    private int value = 0;

    private int placeIndex = -1;

    public Node(Board board, int turn) {
        this.board = new Board((board));
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

    public Node(Node node) {
        this.board = new Board((node.getBoard()));
        this.child = new ArrayList<>(child);
        this.turn = node.getTurn();
        this.value = node.getValue();
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

    public void createAllChilds () {
        for (int i = 0; i < 7; i++) {
            child.add(new Node(board));
            if (turn == -1) {
                child.get(i).setTurn(1);
            } else {
                child.get(i).setTurn(-1);
            }
        }
    }

    public Node createChild(int column, int color, boolean minimilize) {
        Node childNode;
        if (turn == -1) {
            childNode = new Node(this.board, 1);
            child.add(childNode);
//            child.add(new Node(board, 1).getBoard().putDisk(column, color));
        } else {
            childNode = new Node(this.board, 1);
            child.add(childNode);

//            child.add(new Node(board, -1));
        }

        Connect4Fitness connect4Fitness = new Connect4Fitness();
        childNode.getBoard().putDisk(column, color);
//        if (!minimilize) {
//            childNode.setValue(connect4Fitness.calcScore(childNode.getBoard(), color));
//        } else {
//            childNode.setValue(connect4Fitness.calcScore(childNode.getBoard(), color) * -1);
//
//        }
        childNode.setPlaceIndex(column);
        placeIndex = column;

        return childNode;
    }


    public int getPlaceIndex () {
        return placeIndex;
    }

    public void setPlaceIndex (int placeIndex) {
        this.placeIndex = placeIndex;
    }
}
