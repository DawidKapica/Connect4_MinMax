package com.dawidkapica.engine;

import com.dawidkapica.information.Board;
import com.dawidkapica.information.Node;

import java.util.ArrayList;

public class Connect4MinMax {

    private int searchDepth;
    Connect4Fitness connect4Fitness = new Connect4Fitness();
    int maxColor = 0;
    int minColor = 0;


    public Connect4MinMax(int depth) {
        searchDepth = depth;
    }

    public int choosePlace (Board board, int color) {
        if (color == -1) {
            maxColor = -1;
            minColor = 1;
        } else {
            maxColor = 1;
            minColor = -1;
        }

        Node node = new Node(board);
        max(node, 0);
        int indexToPlace = 0;

        node.setValue(Integer.MIN_VALUE);
        int value = node.getValue();
        for (int i = 0; i < node.getChild().size(); i++) {
            System.out.println(node.getChild().get(i).getValue());
            if (node.getChild().get(i).getValue() > value && node.getBoard().checkIsFullColumn(i) == false) {
                value = node.getChild().get(i).getValue();
                indexToPlace = i;
            }
        }

        return indexToPlace;
    }


    public int max(Node node, int depth) {

        node.createChild();
        ArrayList<Integer> fitnesses = connect4Fitness.calculateFitnesses(node.getBoard(), maxColor);
        for (int i = 0; i< node.getChild().size(); i++) {
            node.getChild().get(i).getBoard().putDisk(i, maxColor);
            node.getChild().get(i).setValue(fitnesses.get(i));
            if (depth < searchDepth) {
                min(node.getChild().get(i), depth+1);
            }
        }

        int score = Integer.MIN_VALUE;
        for (Node nodeChild: node.getChild()) {
            if (nodeChild.getValue() > score) {
                score = nodeChild.getValue();
            }
        }
        node.setValue(score);

        return  score;
    }

    public int min(Node node, int depth) {

        node.createChild();
        ArrayList<Integer> fitnesses = connect4Fitness.calculateFitnesses(node.getBoard(), minColor);
        for (int i = 0; i< node.getChild().size(); i++) {
            node.getChild().get(i).getBoard().putDisk(i, minColor);
            node.getChild().get(i).setValue(fitnesses.get(i)*-1);
            if (depth < searchDepth) {
                max(node.getChild().get(i), depth+1);
            }
        }

        int score = Integer.MAX_VALUE;
        for (Node nodeChild: node.getChild()) {
            if (nodeChild.getValue() < score) {
                score = nodeChild.getValue();
            }
        }

        node.setValue(score);
        return  score;
    }
}
